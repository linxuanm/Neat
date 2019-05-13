# Neat
Neat is ~~a mod by Vazkii~~ a beginner-friendly library aimed at making game programming easier.

Designed for educational purposes.

## Main Class
The main game class, used for handling windows, displays, and game scenes is [NeatGame](src/cn/davidma/neat/application/NeatGame.java). This is the main application from which games inherit.

The main program should extend this class. All setup should be done in the {@link #setup()} method. This class should not be explicitly instantiated. The game is launched with ```NeatGame::launch```.