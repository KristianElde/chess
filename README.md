# Chess

Made by Kristian Elde Johansen


## About
Standard Chess game with most modern chess rules implemented. Option to play against the computer and two-player mode.

There are a few missing features. The most prevalent of them being that pawn promotion does not offer the choice to promote to any minor or major piece. Another missing feature is stalemate by repetition. This program has no way of detecting that. 

This was my biggest assignment in the course INF101 at UiB. I really enjoyed having the freedom to make whatever app I wanted to. I also liked the challenge of designing the structure for the packages and classes in order to make the codebase easy to understand, and easily scalable.

## Options
In this application there are several options. Use the main menu to adjust them.
- Texture: Choose whether you want standard chess pieces or a special Star Wars-edition texture pack
- Mode: Choose whether you want to play a two-player game or play against the computer
- Play as: Choose if you want to play as black or white when playing against the computer
- Difficulty: Choose the difficulty level of your opposition when playing against the computer

## AI-difficulty levels
### Easy
On easy the AI will only choose random moves every time. This is the most primitive way of implementing an AI opposition.

### Medium
On medium the AI will be maximally aggressive. The AI wil capture the most valuable piece available with no consideration of what the counter move could be.

### Hard
On hard the AI will be more careful than on medium. It will still optimize for material value, but it is able to consider its oppositions counter move.

## Controllers
- In the main menu: set your preferred settings using the arrow keys.
- In an active game: Use the mouse to click on the piece you want to move. Click again on the square you want to move it to, in order to perform the move.

## Resources
### Star Wars textures
Obtained from: https://no.pinterest.com/pin/happy-star-wars-celebration-day-i-wont-be-attending-but-my-artwork-will-be-there-in-the-form-of-o--292663675784528615/
Originator: Derek Laufman

## Video
Check out this video where i present how the application works: [youtube.com](https://youtu.be/4KGJe_OwlXs)