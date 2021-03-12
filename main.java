import java.util.Scanner;
import java.util.Iterator;
import edu.*;

class myClass{

	//Start
	public static void main(String[] args){

        //Condición: Los mapas no pueden tener ceros en los bordes
        //define map
        char[][] mapa = {{ '1', '1', '1', '1', '1', '1'},  
                        { '1', '1', '1', '0', '0', '1'}, 
                        { '1', '0', '0', '0', 'e', '1'}, 
                        { '1', '0', '0', 'm', '1', '1'}, 
                        { '1', '1', '1', '1', '1', '1'}};//*/

        /*char[][] mapa = {{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}, 
                        { '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1'}, 
                        { '1', '0', '1', '0', '0', '0', '1', '0', '1', '0', '1'}, 
                        { 'e', '0', '1', '0', '0', '0', '0', '0', '1', '0', '1'}, 
                        { '1', '0', '1', '1', '1', '1', '1', '0', '1', '0', '1'}, 
                        { '1', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1'}, 
                        { '1', '0', '0', '0', '1', '0', '1', '0', '0', '0', '1'}, 
                        { '1', '1', '1', '1', '1', '0', '1', '0', '0', '0', '1'}, 
                        { '1', '0', '1', 'm', '1', '0', '1', '0', '0', '0', '1'}, 
                        { '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1'}, 
                        { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}};//*/

        char[][] mapaExp = new char[mapa.length][mapa[0].length];


        //Lista de Agentes
		List lsAgents = new List();

        //coordenadas [x, y]
        int 
        pntStart[] = new int[2],
        pntEnd[]   = new int[2];

        String path = "";

        //Look for start point (m)
        //Look for end point (e)
        //generate mapExp
        for(int j = 0; j < mapa.length; j++){ //y

            for(int i = 0; i < mapa[0].length; i++){ //x

                //start point
                if (mapa[j][i] == 'm'){ 
                    pntStart[0] = i;
                    pntStart[1] = j;

                    mapaExp[j][i] = '0'; //replace
                }

                //end point
                else if (mapa[j][i] == 'e'){ 
                    pntEnd[0] = i;
                    pntEnd[1] = j;

                    mapaExp[j][i] = '0'; //replace
                }
                
                else{
                    mapaExp[j][i] = mapa[j][i]; //copy
                }
            }
        }

        //DESCOMENTAR PARA TENER UNA VISTA PREVIA DE LOS MAPAS
        //printMap(mapa);
        //System.out.println("");
        //printMap(mapaExp);

        //Create the first Agent at start point
        Agent firstAgent = new Agent(pntStart[0], pntStart[1], "");
        lsAgents.add(firstAgent);

        mapaExp[firstAgent.pos[1]][firstAgent.pos[0]] = '2'; //mark as explored

        //path found?
        boolean foundit = false;

        //Give a finite but enough amount of steps
        for (int n = 0; n <= (mapa.length * mapa[0].length); n++){

            if (foundit){
                break;
            }

            //ITERAR LISTA DE AGENTES
            Iterator<ListNode> it = lsAgents.iterator();
            ListNode inode;
            Agent iagent, agentChild;

            while ((inode = it.next()) != null) {

                //agent
                iagent = (Agent) inode.getObject();

                //end reached
                if (equalPoints(iagent.pos, pntEnd)){ 
                    //System.out.println("Im at the end!");
                    path    = iagent.path;
                    foundit = true;
                    break;
                }

                //CHECK UNEXPLORED PLACES

                //right
                if (mapaExp[iagent.pos[1]][iagent.pos[0] +1] == '0'){ 
                    mapaExp[iagent.pos[1]][iagent.pos[0] +1] = '2';

                    agentChild = new Agent(iagent.pos[0] +1, iagent.pos[1], iagent.path);
                    agentChild.MoveRight();

                    lsAgents.add(agentChild);
                }

                //left
                if (mapaExp[iagent.pos[1]][iagent.pos[0] -1] == '0'){ 
                    mapaExp[iagent.pos[1]][iagent.pos[0] -1] = '2';

                    agentChild = new Agent(iagent.pos[0] -1, iagent.pos[1], iagent.path);
                    agentChild.MoveLeft();

                    lsAgents.add(agentChild);
                }

                //up
                if (mapaExp[iagent.pos[1] -1][iagent.pos[0]] == '0'){ 
                    mapaExp[iagent.pos[1] -1][iagent.pos[0]] = '2';

                    agentChild = new Agent(iagent.pos[0], iagent.pos[1] -1, iagent.path);
                    agentChild.MoveUp();

                    lsAgents.add(agentChild);
                }

                //down
                if (mapaExp[iagent.pos[1] +1][iagent.pos[0]] == '0'){ 
                    mapaExp[iagent.pos[1] +1][iagent.pos[0]] = '2';

                    agentChild = new Agent(iagent.pos[0], iagent.pos[1] +1, iagent.path);
                    agentChild.MoveDown();

                    lsAgents.add(agentChild);
                }

                //die
                lsAgents.remove(iagent);

            }
        }

        //print result
        //System.out.println(path);
        pathToCords(path, pntStart);
    }

    //convert path string to coords
    public static void pathToCords(String path, int[] pntStart){

        int[] pntCurrent = {pntStart[0], pntStart[1]};
        String str = "";

        //iterate dirs
        for (char ch : path.toCharArray()) {

            str += printPoint(pntCurrent);

            switch(ch){
                case 'R':
                    pntCurrent[0] += 1;
                break;
                case 'L':
                    pntCurrent[0] -= 1;
                break;
                case 'U':
                    pntCurrent[1] -= 1;
                break;
                case 'D':
                    pntCurrent[1] += 1;
                break;
            }

        }

        str += printPoint(pntCurrent);

        //imprimir cadena de texto con coordenadas
        System.out.println(str);
    }

    //check equal points
    public static boolean equalPoints(int[] pnt1, int[] pnt2){
        return ((pnt1[0] == pnt2[0]) && (pnt1[1] == pnt2[1]));
    }

    //print point
    public static String printPoint(int[] pnt){
        return ("[" + String.valueOf(pnt[1]) + "," + String.valueOf(pnt[0]) + "]");
    }

    //Search for start point (m)
    public static void printMap(char[][] mapa){
        String txt;

        for(int j = 0; j < mapa.length; j++){ //y
            txt = "";

            for(int i = 0; i < mapa[0].length; i++){ //x

                txt += mapa[j][i] + "";
            }
            System.out.println(txt);
        }
    }

}
