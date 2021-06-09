
// import com.cra.figaro.library.atomic.discrete
// import com.cra.figaro.language.Chain
// import com.cra.figaro.library.compound.{RichCPD,OneOf,*} // aici e * in loc de x
// import com.cra.figaro.language.{Flip, Constant, Apply}
// import com.cra.figaro.algorithm.factored.VariableElimination

// object lab6 {
//  def main(args: Array[String]) {

//  def tennis( //functia tennis
// 	 probP1ServeWin: Double, probP1Winner: Double, probP1Error: Double,
// 	 probP2ServeWin: Double, probP2Winner: Double, probP2Error: Double):
//  /*fucntia tennis primeste 6 parametri, fiecare reprezentand
//  variabile aleatoare de tip double, ce retin diferite probabilitati,
//  respectiv probabilitatile ca p1 sau p2 sa serveasca, probabilitatile ca p1 sau p2 sa castige,
//  precum si probabilitatile cu care au gresit lovitura
//  */
//  Boolean = { //tipul returnat , true/false
//  	def rally(firstShot: Boolean, player1: Boolean): Boolean = {
//  		//functie in care p1 si p2 joaca alternativ tenis
//  val pWinner =
//  /* variabila aleatoare ce retine probabilitatea ca p1 sau p2 sa castige */
// 			 if (firstShot && player1) probP1ServeWin
// 			 //p1 serveste primul
// 			 else if (firstShot && !player1) probP2ServeWin
// 			 //p2 serveste primul
// 			 else if (player1) probP1Winner
// 			 //nu mai suntem la prima aruncare (cand avem !firstShot)
// 			 //dar joaca p1, are probabilitate mare sa castige
// 			 else probP2Winner
// 			 //!firstShot- deci nu mai suntem la prima lovitura si p2 joaca,
// 			 // deci are o probabilitata mare de castig

//  val pError = if (player1) probP1Error else probP2Error
//  //variabila aleatoare ce retine probabilitatea ca p1 sau p2 sa rateze/greseasca
//  val winner = Flip(pWinner) // Flip(True) -> ia pWinner ca fiind True
//  //variabila ce retine probabilitatea castigatorului
//  val error = Flip(pError) // Flip(True) -> ia pError ca fiind True
//  //variabila ce retine probabilitatea celui care a gresit

//  if(winner, Constant(player1),
//  	//daca castigatorul este p1
//  	if(error,  Constant(!player1),
//  		//inseamna ca p2 a gresit, iar p1 nu greseste( p2 are eroare )
//  		rally(false, !player1)))

//  		// nu mai suntem in cazul firstShot, ci secondShot, samd
//  		// se continua jocul ( schimb de mingi )
//  		// !player1 indica faptul ca urmeaza randul celui de al doilea sa joace
//  		// se alterneaza loviturile date de cei doi, prin recursivitatea al functiei rally()
//  		// adica se modeleaza jocul, actiunile jucatorilor depind unele de altele, in functie de cat de bine loveste fiecare jucator
// }

//   def game( // pentru exercitiul 5)
// 		 p1Serves: Boolean, p1Points: Element[Int],
// 		 // p1Serves - variabila ce retine faptul daca p1 sau p2 serveste jocul
// 		 // p1Points - variabila ce retine punctele lui p1
// 		 p2Points: Element[Int]): Element[Boolean] = {
//   		 // p2Points - variabila ce  retine punctele lui p2

//  val p1WinsPoint = rally(true, p1Serves)
//  //p1 serveste si loveste primul  , retine un bool, daca p1 ia un punct, sau nu
//  val newP1Points = Apply(p1WinsPoint, p1Points, (wins: Boolean, points: Int) =>
//  	if (wins) points + 1 else points)
//  // p1 castiga puncte noi, sau nu ( punctele se increm cu 1)
//  val newP2Points = Apply(p1WinsPoint, p2Points, (wins: Boolean, points: Int) =>
//  	if (wins) points else points + 1)
//  // p2 castiga puncte noi, sau nu

//  val p1WinsGame = Apply(newP1Points, newP2Points, (p1: Int, p2: Int) =>
//  	p1 >= 4 && p1 - p2 >= 2)
//  // atunci cand se verifica daca p1 e castigator, si anume:
//  // conform regulilor jocului de tenis p1 trebuie sa aiba minim 4 puncte iar diferenta dintre punctele lor(p1-p2) sa fie minim 2 puncte
//  val p2WinsGame = Apply(newP2Points, newP1Points, (p2: Int, p1: Int) =>
//  	p2 >= 4 && p2 - p1 >= 2)
//  // adica atunci cand se verifica daca p2 e castigator, si anume:
//  // conform regulilor jocului de tenis p2 trebuie sa aiba minim 4 puncte iar diferenta dintre punctele lor(p2-p1) sa fie minim 2 puncte
//  val gameOver = p1WinsGame || p2WinsGame
//  //variabila gameOver retine castigatorul jocului

//   if(gameOver, p1WinsGame, game(p1Serves, newP1Points, newP2Points))
// }

//  	def play( // pentru exercitiul 4)
//  p1Serves: Boolean, p1Sets: Element[Int], p2Sets: Element[Int],
//  p1Games: Element[Int], p2Games: Element[Int]): Element[Boolean] = {
//  	// variabile:
//  	/*
//  	p1Serves retine cine serveste jocul
//  	p1Sets - seturile lui p1
//  	p2Sets - seturile lui p2
//  	p1Games - jocurile lui p1
//  	p2Games - jocurile lui p2
//  	*/
//  val p1WinsGame = game(p1Serves, Constant(0), Constant(0))
//  //la inceput incepe cineva si nimeni nu are puncte

//  val newP1Games = Apply(p1WinsGame, p1Games, p2Games, (wins: Boolean, p1: Int, p2: Int) =>
//  	//variab. newP1Games retine noile jocuri ale lui p1
// 				 if (wins) {
// 				 // p1 castiga
// 				 if (p1 >= 5) 0 else p1 + 1
// 				 //si p1 nu are inca cinci jocuri, i se adauga un joc nou
// 				 } else { //p2 castiga
// 				 if (p2 >= 5) 0 else p1 // p2 + 1 ??
// 				 //si p2 castiga si are macar 5 jocuri, p1 ramane neschimbat ( cu acelasi nr de jocuri )
// 				 })

// val newP2Games = Apply(p1WinsGame, p1Games, p2Games, (wins: Boolean, p1: Int, p2: Int) =>
// 	//variab. newP2Games retine noile jocuri ale lui p2
// 				 if (wins) { // p1 castiga
// 				 if (p1 >= 5) 0 else p2
// 				 // si p1 castiga si are macar 5 jocuri, p2 ramane neschimbat ( cu acelasi nr de jocuri )
// 				 } else { // p2 castiga
// 				 if (p2 >= 5) 0 else p2 + 1
// 				 // si p2 castiga si nu are inca 5 jocuri , i se adauga un joc nou
// 				 })

//  val newP1Sets = Apply(p1WinsGame, p1Games, p1Sets,(wins: Boolean, games: Int, sets: Int) =>
//  				if (wins && games == 5) sets + 1 else sets)
//  				// p1 castiga jocul si are sanse sa castige un set
//  val newP2Sets = Apply(p1WinsGame, p2Games, p2Sets,(wins: Boolean, games: Int, sets: Int) =>
//  				if (!wins && games == 5) sets + 1 else sets)
//  	            // p2 castiga jocul si are sanse sa castige un set
//  val matchOver = Apply(newP1Sets, newP2Sets, (p1: Int, p2: Int) => p1 >= 2 || p2 >= 2)
//  /*castigatorul jocului trebuie sa aiba castigate minim doua seturile,
//  asadar, variabila matchOver retine daca p1 a castigat jocul sau p2 a castigat jocul
//  */

//  if(matchOver,
//  	Apply(newP1Sets, (sets: Int) => sets >= 2),
//  	play(!p1Serves, newP1Sets, newP2Sets, newP1Games, newP2Games))
//  //se termina jocul

// }

//  play(true, Constant(0), Constant(0), Constant(0), Constant(0))
// }
//  // incepe p1 jocul , iar nimeni nu are niciun set,sau joc castigat momentan

// }}

// /* Exercitiul 4)
// -> modelam obstinerea jocurilor si a seturilor ( dependenta )
// -> seturile depind de jocuri
// -> castigatorul este cel care castiga doua seturi
// -> exercitiul 4 nu include calculul de puncte luate de fiecare jucator
// -> fiecare jucator are un avantaj mai mare atunci cand loveste primul, adica o probabilitate mai mare sa dea o lovitura buna adeversarului
// -> actiunea lui p2, depinde de actiunea lui p1, apoi actiunea lui p1 depinde de p2, pana cand cineva castiga
// -> functia play() corespunde modelarii seturilor si jocurilor lui p1 si p2

//  val p1WinsGame = game(p1Serves, Constant(0), Constant(0))
//  //la inceput incepe cineva si nimeni nu are puncte

//  modelarea jocurlui lui p1 si p2:

// def play( // pentru exercitiul 4)
//  p1Serves: Boolean, p1Sets: Element[Int], p2Sets: Element[Int],
//  p1Games: Element[Int], p2Games: Element[Int]): Element[Boolean] = {

//  val newP1Games = Apply(p1WinsGame, p1Games, p2Games, (wins: Boolean, p1: Int, p2: Int) =>
//  	//variab. newP1Games retine noile jocuri ale lui p1
// 				 if (wins) {
// 				 // p1 castiga
// 				 if (p1 >= 5) 0 else p1 + 1
// 				 //daca p1 nu are inca cinci jocuri, i se adauga un joc nou
// 				 } else {
// 				 if (p2 >= 5) 0 else p1 // p2 + 1 ??
// 				 //daca p2 castiga si are macar 5 jocuri, p1 ramane neschimbat ( cu acelasi nr de jocuri )
// 				 })

// val newP2Games = Apply(p1WinsGame, p1Games, p2Games, (wins: Boolean, p1: Int, p2: Int) =>
// 	//variab. newP2Games retine noile jocuri ale lui p2
// 				 if (wins) {
// 				 if (p1 >= 5) 0 else p2
// 				 //daca p1 castiga si are macar 5 jocuri, p2 ramane neschimbat ( cu acelasi nr de jocuri )
// 				 } else { // p2 castiga
// 				 if (p2 >= 5) 0 else p2 + 1
// 				 //daca p2 castiga si nu are inca 5 jocuri , i se adauga un joc nou
// 				 })

// modelarea seturilor castigate de p1 si p2:
//  val newP1Sets = Apply(p1WinsGame, p1Games, p1Sets,(wins: Boolean, games: Int, sets: Int) =>
//  				if (wins && games == 5) sets + 1 else sets)

//  val newP2Sets = Apply(p1WinsGame, p2Games, p2Sets,(wins: Boolean, games: Int, sets: Int) =>
//  				if (!wins && games == 5) sets + 1 else sets)


//  concluzia, cine a castigat in functie de numarul de seturi:
//  val matchOver = Apply(newP1Sets, newP2Sets, (p1: Int, p2: Int) => p1 >= 2 || p2 >= 2)

//  if(matchOver,
//  	Apply(newP1Sets, (sets: Int) => sets >= 2),
//  	play(!p1Serves, newP1Sets, newP2Sets, newP1Games, newP2Games))

// */

// /* Exercitiul 5)
//  -> sunt introduse in acest model si colectarea de puncte si modul in care pot fi luate aceste puncte de catre p1 si p2
//  -> functia game() modeleaza acest concept de strangere a noilor puncte
//  -> de aseamenea si aici ne legam de dependenta a trei factori , seturi, jocuri, puncte
//  -> seturile depind de jocurile castigate sau pierdute , iar jocurile la randul lor depind de numarul de puncte obtinute
// def game(  p1Serves: Boolean, p1Points: Element[Int],
// 		 p2Points: Element[Int]): Element[Boolean] = {


//  val p1WinsPoint = rally(true, p1Serves)

//  incepe modelarea punctelor:
//  val newP1Points = Apply(p1WinsPoint, p1Points, (wins: Boolean, points: Int) =>
//  	if (wins) points + 1 else points)
//  // p1 castiga puncte noi, sau nu, in functie de faptul daca da o lovitura castigatoare sau nu
//  val newP2Points = Apply(p1WinsPoint, p2Points, (wins: Boolean, points: Int) =>
//  	if (wins) points else points + 1)
//  // p2 castiga puncte noi, sau nu, in functie de faptul daca da o lovitura castigatoare sau nu


// acum incepe modelarea jocurilor in functie de punctele obtinute:
//  val p1WinsGame = Apply(newP1Points, newP2Points, (p1: Int, p2: Int) =>
//  	p1 >= 4 && p1 - p2 >= 2)
//  // p1 e castigator
//  val p2WinsGame = Apply(newP2Points, newP1Points, (p2: Int, p1: Int) =>
//  	p2 >= 4 && p2 - p1 >= 2)
//  // p2 e castigator
//  val gameOver = p1WinsGame || p2WinsGame
//  //castigatorul jocului p1 sau p2

//   if(gameOver, p1WinsGame, game(p1Serves, newP1Points, newP2Points))
// }

// */

// */

// def tennisEx4( //functia tennis
// 	 probP1ServeWin: Double, probP1Winner: Double,
// 	 probP2ServeWin: Double, probP2Winner: Double):
// 	  Boolean = {
// 	  	def play(
// 			 	//returneaza true/false , incepe jocul !
// 			 p1Serves: Boolean, p1Sets: Element[Int], p2Sets: Element[Int],
// 			 p1Games: Element[Int], p2Games: Element[Int]): Element[Boolean] = {


// 	  	 val newP1Sets = Apply(p1WinsGame, p1Games, p1Sets,(wins: Boolean, games: Int, sets: Int) =>
//  				if (wins && games == 5) sets + 1 else sets)
//  				//daca p1 castiga jocul si are 5 jocuri castigate, castiga un set
// 		 val newP2Sets = Apply(p1WinsGame, p2Games, p2Sets,(wins: Boolean, games: Int, sets: Int) =>
// 		 				if (!wins && games == 5) sets + 1 else sets)
// 		 	            //daca p2 castiga jocul si are 5 jocuri castigate, castiga un set
// 		 val matchOver = Apply(newP1Sets, newP2Sets, (p1: Int, p2: Int) => p1 >= 2 || p2 >= 2)
// 		 /*castigatorul jocului trebuie sa aiba castigate minim doua seturile,
// 		 asadar, variabila matchOver retine daca p1 a castigat jocul sau p2 a castigat jocul
// 		 */
// 	  	}

// 	  }

// //cred ca totul mai putin partea cu rally , error, serving ability ?

// //pentru ex 5 -> intervine si partea cu points pentru p1 si p2

