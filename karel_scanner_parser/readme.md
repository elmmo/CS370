## Karel Scanner and Parser

### Scanner 
**Scanner Grammar**  
_program -> stmtlist_  
_stmtlist -> stmt stmtlist | epsilon_  
_stmt -> move | turn | decl | assign | take | drop_  
_move -> move(cnt)_  
_turn -> turn(dir)_  
_decl -> var id_  
_assign -> id := cnt_  
_id -> $[a-z]_  
_cnt -> 0 | 1 | 2 | 3 | 4 | 5_  
_dir -> l | r_  

**Scanner Token Generation**  
This scanner will generate a dynamic list of the following tokens:  
* move 
* turn 
* take 
* drop 
* var
* id 
* := 
* (
* )
* dir 
* cnt 

This design decision was made with the goal of ease of use and accuracy of the scanner and parser.

**Design Decisions**  
* The Scanner is whitespace-sensitive. This decision was made because of the simple, whole-word nature of the grammar
* Input must be entered on a single line
