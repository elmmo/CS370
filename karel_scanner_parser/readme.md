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

There is a little bit of ambiguity with the way the grammar is defined, so I wrote the scanner with the lowest possible level of abstraction. **The parser is not written this way**. The parser will start from the highest level of abstraction and work downward. 

**Other Design Decisions**  
* Input must be entered line by line. After entering the first line, you will be asked if you would like to enter more, and any other tokens detected will be added _in addition to_ whatever tokens were detected before.  

### Parser 
There is nothing in the parser that would require it to have access to the symbol table, so the parser is not configured to use the symbol table in any way. 

Since the parser and scanner were written separately based on the needs for them (see above), the two are not currently connected. 


