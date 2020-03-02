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

**Sample Inputs**   
* ```turn(l) move(4) turn(r) move(3) var hello := 3 move(4) take```
* ```take turn(r) move(5) drop```


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
* Input must be entered on a single line
