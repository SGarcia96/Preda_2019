package ped1;
/*
 * Esta clase se encarga de construir los cuadrantes de la tabla del torneo y aplica la técnica de Divide y Vencerás
 */
public class Cuadrantes {

	/**Si n es potencia de 2, implementar algoritmo para construir cuadrante de partidas del torneo 
	para terminarlo en n-1 dias
	 *
	 * @param n es el número máximo de jugadores
	 * @param tabla matriz que contiene en la primera Dimensión el número de jugadores, y en la segunda dimensión el número de días
	 * @param t variable que se usa para trazar cada invocación recursiva si se introduce -t al ejecutar el torneo, si t=1 se ejecuta, si no, no
	 */
	public void construirCuadrantes(int n, int tabla[][], int t) {
		if (t == 1) {
			System.out.println("n= "+ n); //se imprime la traza (valor de n en cada llamada recursiva)
		}
		if (n==2) { // Caso base (solo quedan 2 jugadores)
			tabla[0][0] = 2;
			tabla[1][0] = 1;
		}
		else { // Si hay más de 2 jugadores, y es potencia de 2, aplicamos la técnica del Divide y Vencerás
			//Se rellena el cuadrante superior izquierdo
			construirCuadrantes(n/2, tabla, t); //Llamada recursiva, dividimos el problema por la mitad 
			combinar(n, tabla);
		}
	}
	
	//Función que combina los subproblemas resueltos para construir la tabla
	private void combinar(int m, int tabla[][]) {
		//Se rellena el cuadrante inferior izquierdo
		for (int jug=((m/2)); jug<m; jug++) {
			for (int dia=0; dia<(m/2)-1; dia++) {
				tabla[jug][dia] = tabla[jug-(m/2)][dia]+(m/2); //se suma n/2 a los valores del cuadrante superior izquierdo
			}
		}
		
		//Luego se rellena el cuadrante superior derecho, enfrentando a los jugadores 1..n/2 contra jugadores (n/2)+1..n
		for (int jug=0; jug<m/2; jug++) {
			for (int dia=(m/2)-1; dia<m-1; dia++) {
				if ((jug+1)+(dia+1) <= m) {
					tabla[jug][dia] = (jug+1)+(dia+1);
				}
				else {
					tabla[jug][dia] = (jug+1)+(dia+1) - (m/2);
				}
			}
		}
		
		//Finalmente, se rellena el cuadrante inferior derecho, enfrentando a los jugadores de mayor número, contra los de menor número
		//enfrentando a los jugadores (n/2)+1..n contra 1..n/2 en el día n/2, y rotando los valores 1..n cada día
		for (int jug=(m/2); jug<m; jug++) {
			for (int dia=(m/2)-1; dia<m-1; dia++) {
				if (jug>dia) {
					tabla[jug][dia] = jug-dia;
				}
				else {
					tabla[jug][dia] = (jug+(m/2))-dia;
				}
			}
		}
	}
}
