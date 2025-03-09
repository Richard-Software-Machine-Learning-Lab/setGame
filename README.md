# Features

## Player Features
* Player Registration: A player must enter their name before starting the game.
* Start Game: A player can initiate a new game session.
* Track Player Progress: Each player's progress is tracked for each challenge.
* Leaderboard Participation: A player can appear multiple times on a challenge’s leaderboard.

## Challenge Features
* Challenge Generation: Challenges are pre-generated and stored in an array list.
* Independent Challenges: Each challenge is independent and may contain the same cards.
* Challenge Timer: A timer starts when a player begins a challenge and stops when all sets are found.
* Leaderboard Per Challenge: Each challenge maintains a leaderboard ranking the top 10 fastest players.
* New Challenge After Completion: Once a challenge is completed, a new one is generated (within a possible challenge limit).

## Card & Set Features
* Card Display: 12 cards are placed face-up at the start of each challenge.
* Set Validation: A set is valid if the three selected cards are either completely identical or completely different in all four attributes (color, shape, line, and number).
* Multi-Set Inclusion: A card can belong to multiple sets.
* Set Removal: Cards are not replaced after a set is found.
* Guaranteed Set Presence: Every challenge is guaranteed to have at least one valid set.

## Game Mechanics
* Card Selection: Players can select up to three cards at a time.
* Validation of Selection: Upon confirmation, the selection is checked for validity as a set.
* Valid Set: The set is counted, and the player is notified.
* Invalid Set: The selection is removed, and the player can try again.
* Challenge Completion Notification: Players are notified once all sets are found.
* Leaderboard Update: A player’s completion time is added to the challenge leaderboard if it is among the top 10 times.

## Implementation Features
* Test-Driven Development (TDD): The project follows an incremental, test-driven development approach.
* Mockito for Testing: Mockito is used for testing mock objects.
* Model-View Separation: The game model is separate from the RESTful API implementation.
* Spring Framework for API: The backend uses the Spring framework for RESTful API implementation.
* Swing-based GUI: Java Swing is used for the graphical user interface.
* REST API for Communication: The front-end interacts with the backend via RESTful API calls.