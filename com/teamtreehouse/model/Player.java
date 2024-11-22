package com.teamtreehouse.model;

public class Player implements Comparable<Player> {
  private String firstName;
  private String lastName;
  private int heightInInches;
  private boolean previousExperience;

  public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.heightInInches = heightInInches;
    this.previousExperience = previousExperience;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getHeightInInches() {
    return heightInInches;
  }

  public boolean isPreviousExperience() {
    return previousExperience;
  }

  @Override
  public int compareTo(Player other) {
    // Sort by first name, then by last name
    int firstNameComparison = this.firstName.compareTo(other.firstName);
    if (firstNameComparison == 0) {
      return this.lastName.compareTo(other.lastName);
    }
    return firstNameComparison;
  }

  @Override
  public String toString() {
    return String.format("%s %s (%d inches - %s)",
            firstName, lastName, heightInInches, previousExperience ? "Experienced" : "Inexperienced");
  }
}
