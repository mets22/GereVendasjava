import java.util.ArrayList;
import java.util.List;

import static Leitor.Input.lerInt;

public class Menu {
    private List<String> opcoes;
    private int op;

    public Menu(String [] opcoes){
        this.opcoes = new ArrayList<String >();
        for(String op:opcoes){
            this.opcoes.add(op);
        }
        this.op = 0;
    }

    public void executa(){
        do{
            showMenu();
            this.op = lerOpcao();
        }while(this.op == -1);
    }

    public void showTitle(){
        System.out.println("####### HIPERMERCADO #######");
    }

    private void showMenu(){
        System.out.println("############################");
        for(int i=0;i<this.opcoes.size();i++){
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }

    private int lerOpcao(){
        int op;

        System.out.print("Opção:");
        op=lerInt();

        if(op<0 || op>this.opcoes.size()){
            System.out.println("Opção inválida");
            op = -1;
        }
        return op;
    }

    public void setOpcao(int op){this.op = op;}

    public int getOpcao(){return this.op;}
}
