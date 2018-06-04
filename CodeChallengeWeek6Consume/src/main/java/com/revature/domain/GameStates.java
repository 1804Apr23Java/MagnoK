package com.revature.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameStates {
	
	private int game_Id;
	private String deckState;
	private int currentTurn;
	private int pot;
	private int status;
	private int timeLeft;
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
