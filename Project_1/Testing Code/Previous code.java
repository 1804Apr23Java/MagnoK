HttpSession session = request.getSession(false);
		// Get username from login servlet
		String username = (String) session.getAttribute("username");

		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		EmployeeDao ed = new EmployeeDaoImpl();

		Employee e = ed.getEmployeeByUsername(username);

		// Get values for Reimbursement Submission
		String reqNotes = request.getParameter("username");
		String amt = request.getParameter("password");

		System.out.println(reqNotes);
		System.out.println(amt);

		BigDecimal amount = new BigDecimal("0");
		String fName = "No file has been uploaded";

		Reimbursements r = rd.createReimbursement(e, new Reimbursements(0, null, reqNotes, amount, null, fName));

		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> files = null;
		try {
			files = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}

		// Only uploads if there is a file
		if (files.get(0).getSize() > 10) {
			try {
				for (FileItem item : files) {
					String ext = FilenameUtils.getExtension(item.getName());
					item.write(new File("C:\\GitRepos\\MagnoK\\Project_1\\src\\main\\webapp\\images\\"
							+ String.valueOf(r.getId() + "." + ext)));
					fName = String.valueOf(r.getId()) + "." + ext;
					System.out.println(item.getSize());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			r.setImg(fName);
			System.out.println("File Uploaded");
		}

		response.setContentType("application/json");
		ObjectMapper om = new ObjectMapper();
		String reimbursementInfo = om.writeValueAsString(r);
		response.getWriter().write(reimbursementInfo);
