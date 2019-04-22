# SirTet
### ~Tetris Project

## Description

SirTet est un Tetris écrit en java.
C'est une solution qui répond au kata de qualité logiciel ci-contre : https://github.com/ygrenzinger/polytech-course-starter

La solution propose un Back solide non dépendant de l'IHM. Effectivement les actions *cycliques* (création de pièces, descente automatique, suppression...) s'effectuent sans adhérence avec l'IHM qui ne sert qu'à la représentation et aux éventements de déplacement des formes.
Le reste est géré à 100% par le Back.

## Réponse aux exigences

|Exigences| Status|
|--|--|
| *create a board with dimensions 10 by 2* | **DONE** |
|_different pieces of 4 contiguous blocks_|**DONE** *(7 Pièces disponibles)* |
|_create a new piece_|**DONE**|
|_move a piece down the board_|**DONE** *(starting delay : 0.5s)* |
|_stop a piece from advancing further_|**DONE**
| _clear complete lines_|**DONE**|
|_collapse cleared lines_|**DONE**|
|_rotate pieces_|**DONE** *(left rotation)*|
|_move pieces horizontally_|**DONE** *(use front)*|
|_end the game_|**DONE**|
|_keep track of my score_|**Not implemented**|
|_view the board_|**DONE**|
|_increase level_|**DONE** *(delay - 2 * deleted lines)*
|_move pieces down quickly_|**DONE** *(down arrow)*




