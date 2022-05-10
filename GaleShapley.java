

public class GaleShapley
{
    private int N, contador_empare;
    private String[][] preferencia_hombres;
    private String[][] preferencia_mujeres;
    private String[] hombres;
    private String[] mujeres;
    private String[] pareja_mujer;
    private boolean[] hombres_comprometidos;
    
    
 
    public static void main(String[] args) 
    {
  
        /* lista de hombres */
        String[] m = {"Victor", "William", "Xavier", "Yancey", "Zeus"};
        
        /*lista de mujeres*/
        String[] w = {"Amy", "Bertha", "Claire", "Diane", "Erika"};
        
 
        /*preferencias de hombres*/
        String[][] mp = {{"Bertha",	 	"Amy", 		"Diane",	"Erika", 		"Claire"}, 
                         {"Diane", 		"Bertha", 	"Amy", 		"Claire", 		"Erika"}, 
                         {"Bertha", 	"Erika", 	"Claire", 	"Diane", 	 	"Amy"}, 
                         {"Amy", 		"Diane",	"Claire", 	"Bertha", 		"Erika"},
                         {"Bertha", 	"Diane", 	"Amy", 		"Erika", 		"Claire"}};
        
        /*preferencia de mujeres*/                      
        String[][] wp = {{"Zeus", 		"Victor", 	 	"William", 		"Yancey", 		"Xavier"}, 
                         {"Xavier", 	"William", 		"Yancey", 		"Victor", 		"Zeus"}, 
                         {"William", 	"Xavier", 		"Yancey", 		"Zeus", 		"Victor"},
                         {"Victor",	 	"Zeus", 		"Yancey", 		"Xavier", 		"William"}, 
                         {"Yancey",	 	"William", 		"Zeus", 		"Xavier", 		"Victor"}};
        
 
        GaleShapley emparejamiento = new GaleShapley(m, w, mp, wp);                      
        
    }
 
  
    public GaleShapley(String[] h, String[] m, String[][] ph, String[][] pm)
    {
        N = ph.length;
        contador_empare = 0;
        hombres = h;
        mujeres = m;
        preferencia_hombres = ph;
        preferencia_mujeres = pm;
        hombres_comprometidos = new boolean[N];
        pareja_mujer = new String[N];
        hallar_emparejamiento();
    }
    
    /*metodo para  hallar todos los emparejamientos*/
    private void hallar_emparejamiento()
    {
        while (contador_empare < N)
        {
            int free;
            for (free = 0; free < N; free++)
                if (!hombres_comprometidos[free])
                    break;
 
            for (int i = 0; i < N && !hombres_comprometidos[free]; i++)
            {
                int index = mujeresIndexOf(preferencia_hombres[free][i]);
                if (pareja_mujer[index] == null)
                {
                    pareja_mujer[index] = hombres[free];
                    hombres_comprometidos[free] = true;
                    contador_empare++;
                }
                else
                {
                    String currentPartner = pareja_mujer[index];
                    if (morePreference(currentPartner, hombres[free], index))
                    {
                        pareja_mujer[index] = hombres[free];
                        hombres_comprometidos[free] = true;
                        hombres_comprometidos[hombresIndexOf(currentPartner)] = false;
                    }
                }
            }            
        }
        mostrarParejas();
    }

    /* Metodo para verificar si la mujer prefiere una nueva pareja a la antigua pareja asignada */
    private boolean morePreference(String curPartner, String newPartner, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (preferencia_mujeres[index][i].equals(newPartner))
                return true;
            if (preferencia_mujeres[index][i].equals(curPartner))
                return false;
        }
        return false;
    }
    /*Metodo para obtener el índice de la posicion hombres*/
    private int hombresIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (hombres[i].equals(str))
                return i;
        return -1;
    }
    /*Metodo para obtener índice de la posicion mujeres*/
    private int mujeresIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (mujeres[i].equals(str))
                return i;
        return -1;
    }
    /* metodo para imprimir las parejas formadas */
    public void mostrarParejas()
    {
        System.out.println("Emparejamientos : ");
        for (int i = 0; i < N; i++)
        {
            System.out.println(pareja_mujer[i] +"\t ------->\t"+ mujeres[i]);
        }
    }
 
}
