% first names: Howard, Ryan, Barry, Adam, Sydney

% last names: Right, Straight, Element, Rafe, Chariot
ranking([
    team(_, element, _, _)
]).

% sponsors: Flash Automotive, NAPA Auto Parts, Crank Motor Oil, Tredco Tires, Fleet Bodyworks
ranking([
    team(_, _, tredcoTires, _),
    team(_, _, fleetBodyworks, _)
]).

% positions: 1-5
ranking([
    team(_, _, _, 5)
]).

puzzle(Ranking) :-
    ranking(Ranking),

    % the driver sponsored by Flash Automotive finished in third place
    member(rank(_, _, flashAutomotive, 3), Ranking),

    % barry straight wasn't sponsored by fleet
    member(rank(barry, straight, BarrySponsor, _), Ranking), BarrySponsor \= fleetBodyworks,

    % napa sponsored the driver who finished fourth, which wasn't barry
    member(rank(NapaName, _, napaAutoParts, 4), Ranking), NapaName \= barry,

    % five drivers in no particular order: adam, 2nd place, tredco tires, right, rafe
    member(rank(adam, AdamLName, AdamSponsor, AdamRank), Ranking), AdamLName \= right, AdamLName \= rafe, AdamSponsor \= tredcoTires, AdamRank \= 2,
    member(rank(_, rafe, RafeSponsor, RafeRank), Ranking), RafeSponsor \= tredcoTires, RafeRank \= 2,
    member(rank(_, _, SecondSponsor, 2), Ranking), SecondSponsor \= tredcoTires,

    % ryan's last name wasn't right, and he wasn't sponsored by napa
    member(rank(ryan, RyanLName, RyanSponsor, _), Ranking), RyanLName \= right, RyanSponsor \= napaAutoParts,

    % howard placed one position lower than right
    % howard wasn't sponsored by crank and didn't finish in fifth place
    member(rank(_, right, RightSponsor, RightRank), Ranking), RightSponsor \= tredcoTires, RightRank \= 2, RightRank = (HowardRank+1),
    member(rank(howard, _, HowardSponsor, HowardRank), Ranking), HowardSponsor \= crankMotorOil, HowardRank \= 5, HowardRank = (RightRank-1),

    % the driver sponsored by crank placed one position higher than sydney
    % sydney's last name wasn't element
    member(rank(sydney, SydLName, _, SydRank), Ranking), SydLName \= element, SydRank = (CrankRank-1),
    member(rank(_, _, crankMotorOil, CrankRank), Ranking), CrankRank = (SydRank+1),

    % chariot's first name wasn't adam, and he finished in first place
    member(rank(ChariotName, chariot, _, 1), Ranking), ChariotName \= adam,

    printRanking(Ranking).
           
printRanking([A | B]) :- write(A), nl, printRanking(B).
printRanking([]).

