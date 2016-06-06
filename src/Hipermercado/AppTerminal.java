package Hipermercado;

import Leitor.Input;


import java.io.IOException;

public class AppTerminal {
    private static Menu menuInicial, menuDados, menuLerFicheiros, menuGuardarEstado, menuRestaurarEstado, menuEstatisticas;


    private static Hipermercado hipermercado;

    private static String ficheiroClientes = "C:\\Users\\Nuno\\Documents\\GitHub\\GereVendasjava\\src\\Clientes.txt";
    private static String ficheiroProdutos = "C:\\Users\\Nuno\\Documents\\GitHub\\GereVendasjava\\src\\Produtos.txt";
    private static String ficheiroVendas = "C:\\Users\\Nuno\\Documents\\GitHub\\GereVendasjava\\src\\Vendas_1M.txt";
    private static String ficheiroEstado = "C:\\Users\\Nuno\\Documents\\GitHub\\GereVendasjava\\src\\hipermercado.dat";

    public static void main(String args[]){
        carregarMenus();

        do{
            menuInicial.showTitle();
            menuInicial.executa();
            switch (menuInicial.getOpcao()){
                case 1: menuDadosStart();
                    break;
                case 2: menuEstatisticas.executa();
                    break;
            }
        }while (menuInicial.getOpcao()!=0);

    }

    public static void carregarMenus(){
        String [] minicial = {
                "Dados",
                "Consultas Estatísticas",
                "Consultas Interactivas"
        };

        String [] mdados = {
                "Ler ficheiros",
                "Guardar estado",
                "Recuperar estado"
        };

        String [] mlerficheiros = {
                "Fazer a leitura",
                "Alterar nome de ficheiro de Clientes",
                "Alterar nome de ficheiro de Produtos",
                "Alterar nome de ficheiro de Vendas",
                "Alterar todos os nomes de ficheiros"
        };

        String [] mguardarestado = {
                "Guardar estado",
                "Guardar estado com outro nome"
        };

        String [] mrestaurarestado = {
                "Restaurar estado",
                "Restaurar estado com outro nome"
        };

        String [] mestatísticas = {
                "Dados referentes ao último ficheiro lido",
                "Números gerais respeitantes aos dados actuais"
        };

        menuInicial = new Menu(minicial);
        menuDados = new Menu(mdados);
        menuLerFicheiros = new Menu(mlerficheiros);
        menuGuardarEstado = new Menu(mguardarestado);
        menuRestaurarEstado = new Menu(mrestaurarestado);
        menuEstatisticas = new Menu(mestatísticas);
    }


    public static void menuDadosStart(){

        do{
            menuDados.showTitle();
            menuDados.executa();
            switch (menuDados.getOpcao()){
                case 1: menuLerFicheirosStart();
                        break;
                case 2: menuGuardarEstadoStart();
                        break;
                case 3: menuRestaurarEstadoStart();
                        break;
            }
        }while (menuDados.getOpcao()!=0);
    }

    public static void menuLerFicheirosStart(){

        do{
            menuLerFicheiros.showTitle();
            System.out.println("Ficheiro de Clientes:" + ficheiroClientes);
            System.out.println("Ficheiro de Produtos:" + ficheiroProdutos);
            System.out.println("Ficheiro de Vendas:" + ficheiroVendas);
            menuLerFicheiros.executa();
            switch (menuLerFicheiros.getOpcao()){
                case 1: hipermercado.lerFicheiros(ficheiroClientes,ficheiroProdutos,ficheiroVendas);
                        menuLerFicheiros.setOpcao(0);
                        break;
                case 2: System.out.print("Novo nome de ficheiro de clientes:");
                        ficheiroClientes = Input.lerString();
                        break;
                case 3: System.out.print("Novo nome de ficheiro de produtos:");
                        ficheiroProdutos = Input.lerString();
                        break;
                case 4: System.out.print("Novo nome de ficheiro de vendas:");
                        ficheiroVendas = Input.lerString();
                        break;
                case 5: System.out.print("Novo Nome de ficheiro de clientes:");
                        ficheiroClientes = Input.lerString();
                        System.out.print("Novo nome de ficheiro de produtos:");
                        ficheiroProdutos = Input.lerString();
                        System.out.print("Novo nome de ficheiro de vendas:");
                        ficheiroVendas = Input.lerString();
                        break;
            }
        }while (menuLerFicheiros.getOpcao()!=0);
    }


    public static void menuGuardarEstadoStart(){

        do{
            menuGuardarEstado.showTitle();
            System.out.println("Nome do ficheiro de estado:" + ficheiroEstado);
            menuGuardarEstado.executa();
            switch (menuGuardarEstado.getOpcao()) {
                case 1:
                    try {
                        hipermercado.gravaEstado(ficheiroEstado);
                    } catch (IOException e) {
                        System.out.println("Não foi possível guardar o estado do programa");
                    }
                    menuGuardarEstado.setOpcao(0);
                    break;
                case 2:
                    System.out.print("Nome do ficheiro de estado:");
                    ficheiroEstado = Input.lerString();
                    break;
            }
        }while(menuGuardarEstado.getOpcao()!=0);
    }

    public static void menuRestaurarEstadoStart(){

        do{
            menuRestaurarEstado.showTitle();
            System.out.println("Nome do ficheiro de estado:" + ficheiroEstado);
            menuRestaurarEstado.executa();
            switch (menuRestaurarEstado.getOpcao()){
                case 1: try{
                        hipermercado = Hipermercado.carregaEstado(ficheiroEstado);
                        }catch (IOException e){
                        System.out.println("Erro ao abrir o ficheiro de estado");
                        }catch (ClassNotFoundException e){
                            e.printStackTrace();
                        }
                        menuRestaurarEstado.setOpcao(0);
                        break;
                case 2: System.out.print("Nome do ficheiro de estado:");
                        ficheiroEstado = Input.lerString();
                        break;
            }
        } while (menuRestaurarEstado.getOpcao()!=0);
    }

}
