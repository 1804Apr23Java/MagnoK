// First attempt at file upload
HttpSession session = request.getSession(false);
		// Get username from login servlet
		String username = (String) session.getAttribute("username");

		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		EmployeeDao ed = new EmployeeDaoImpl();

		Employee e = ed.getEmployeeByUsername(username);

		// Get values for Reimbursement Submission
		String reqNotes = request.getParameter("requestNotes");
		String amt = request.getParameter("amount");
		BigDecimal amount = new BigDecimal("0");
		String fName = "No file has been uploaded";

		// Check that we have a file upload request (will only get file if it is
		// present)
		isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			file = null;
		} else {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxMemSize);

			// Location to save data that is larger than maxMemSize.
			factory.setRepository(new File("c:\\temp"));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize);

			try {
				// Parse the request to get file items.
				List fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// Get the uploaded file parameters
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						fName = fileName;
						String contentType = fi.getContentType();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();

						// Write the file
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
						} else {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
						}
						fi.write(file);
					}
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

		Reimbursements r = rd.createReimbursement(e, new Reimbursements(0, null, reqNotes, amount, null, fName));
		response.setContentType("application/json");
		ObjectMapper om = new ObjectMapper();
		String reimbursementInfo = om.writeValueAsString(r);
		response.getWriter().write(reimbursementInfo);