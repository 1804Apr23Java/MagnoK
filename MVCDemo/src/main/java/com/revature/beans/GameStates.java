package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GAMESTATES")
public class GameStates implements Serializable{

	public GameStates() {
		super();
	}

	public GameStates(String deckState, int currentTurn, int pot, int status, int timeLeft, String tableState) {
		super();
		this.deckState = deckState;
		this.currentTurn = currentTurn;
		this.pot = pot;
		this.status = status;
		this.timeLeft = timeLeft;
		this.tableState = tableState;
	}

	public GameStates(int game_Id, String deckState, int currentTurn, int pot, int status, int timeLeft,
			String tableState) {
		super();
		this.game_Id = game_Id;
		this.deckState = deckState;
		this.currentTurn = currentTurn;
		this.pot = pot;
		this.status = status;
		this.timeLeft = timeLeft;
		this.tableState = tableState;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6995807884107640690L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameSequence")
	@SequenceGenerator(allocationSize = 1, name = "gameSequence", sequenceName = "SQ_GAME_PK")
	@Column(name = "GAME_ID")
	private int game_Id;
	
	@Column(name = "DECK_STATE")
	private String deckState;
	
	@Column(name = "CURRENT_TURN")
	private int currentTurn;
	
	@Column(name = "POT")
	private int pot;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "TIME_LEFT")
	private int timeLeft;
	
	@Column(name = "TABLE_STATE")
	private String tableState;

	public int getGame_Id() {
		return game_Id;
	}

	public void setGame_Id(int game_Id) {
		this.game_Id = game_Id;
	}

	public String getDeckState() {
		return deckState;
	}

	public void setDeckState(String deckState) {
		this.deckState = deckState;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public String getTableState() {
		return tableState;
	}

	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

	@Override
	public String toString() {
		return "GameStates [game_Id=" + game_Id + ", deckState=" + deckState + ", currentTurn=" + currentTurn + ", pot="
				+ pot + ", status=" + status + ", timeLeft=" + timeLeft + ", tableState=" + tableState + "]";
	}
}
