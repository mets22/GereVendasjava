package Leitor;


import Cliente.Cliente;
import Produto.Produto;
import Filial.Venda;

import Cliente.CatClientes;
import Produto.CatProdutos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LeitorFicheiros {

    /*public static void main(String args[]){
        ArrayList<Venda> teste;
        HashSet<Venda> teste1;

        ArrayList<String> resultados = readLinesWithBuff("//home/nfernandes/Documentos/GitHub/GereVendasjava/src/resources/Vendas_3M.txt");

        teste = parseAllLinhas(resultados);
        teste1 = parseAllLinhasToSet(resultados);

        System.out.println(teste.get(teste.size()-1).toString());
        System.out.println("Size array resultante:" + teste.size());

        System.out.println("Size set resultante:"+teste1.size());
        Crono.start();
        ArrayList<String> arraycena = readLinesArrayWithScanner("//home/nfernandes/Documentos/GitHub/GereVendasjava/src/resources/Vendas_3M.txt");
        Crono.stop();
        System.out.println("linhas lidas:"+arraycena.size() + " Tempo:" + Crono.print());

        Crono.start();
        ArrayList<String> arraybuff = readLinesWithBuff("/home/nfernandes/Documentos/GitHub/GereVendasjava/src/resources/Vendas_3M.txt");
        Crono.stop();
        System.out.println("linhas lidas:"+arraybuff.size() + " Tempo:" + Crono.print());

    }*/



    public static List<String> readLinesArrayWithScanner(String ficheiro){
        List<String> linhas = new ArrayList<>();
        Scanner scanFile = null;

        try{
            String delimiter = System.getProperty("line.separator");
            scanFile = new Scanner(new FileReader(ficheiro));
            scanFile.useDelimiter(delimiter);
            while (scanFile.hasNext()) linhas.add(scanFile.nextLine());
        }catch (IOException e){
            System.out.println(e.getMessage());return null;
        }finally {
            return linhas;
        }
    }


    public static List<String> readLinesWithBuff(String fich) {
    List<String> linhas = new ArrayList<>();
    BufferedReader inStream = null;
    String linha = null;
    try {
        inStream = new BufferedReader(new FileReader(fich));
        while( (linha = inStream.readLine()) !=  null )
            linhas.add(linha);
    }catch(IOException e) {
        System.out.println(e.getMessage());
        return null;
    }
    return linhas;
    }



    /** Métodos de parsing para o ficheiro de produtos **/
    public static CatProdutos parseFicheiroProdutos(String fich){
        List<String> parsed = readLinesWithBuff(fich);
        CatProdutos res = parseAllLinhasProdutos(parsed);
        return res;
    }

    public static CatProdutos parseAllLinhasProdutos(List<String> linhas){
        CatProdutos res = new CatProdutos();
        for(String s:linhas){
            Produto produto = parseLinhaProduto(s);
            res.insereprod(produto);
        }
        return res;
    }


    public static Produto parseLinhaProduto(String linha){
        Produto produto;
        linha.trim();
        produto = new Produto(linha);
        return produto;
    }

    /** Métodos de parsing para o ficheiro de Clientes **/

    public static CatClientes parseFicheiroClientes(String fich){
        List<String> parsed = readLinesWithBuff(fich);
        CatClientes res = parseAllLinhasClientes(parsed);
        return res;
    }


    public static CatClientes parseAllLinhasClientes(List<String> linhas){
        CatClientes res = new CatClientes();
        for(String s:linhas){
            Cliente cliente = parseLinhaCliente(s);
            res.inserecli(cliente);
        }
        return res;
    }

    public static Cliente parseLinhaCliente(String linha){
        Cliente cliente;
        linha.trim();
        cliente = new Cliente(linha);

        return cliente;
    }


    /** Métodos para o parsing das vendas **/

    public static Venda parseLinhaVenda(String linha){
        Venda venda;

        Cliente cliente;
        Produto produto;
        boolean NP;
        double preço;
        int mes;
        int quantidade;
        int filial;
        linha.trim();
        String[] aux = linha.split(" ");

        produto = new Produto(aux[0]);
        preço = Double.parseDouble(aux[1]);
        quantidade = Integer.parseInt(aux[2]);
        if(aux[3].equals("P")) NP = true;
        else NP =false;
        cliente = new Cliente(aux[4]);
        mes = Integer.parseInt(aux[5]);
        filial = Integer.parseInt(aux[6]);

        venda = new Venda(filial,produto,cliente,preço,quantidade,NP,mes);

        return venda;
    }

    public static List<Venda> parseAllLinhasVendas(List<String> linhas){
        List<Venda> res = new ArrayList<>();

        for (String s:linhas){
            Venda v = parseLinhaVenda(s);
            res.add(v);
        }

        return res;
    }

    public static Set<Venda> parseAllLinhasToSet(List<String> linhas){
        Set<Venda> res = new HashSet<>();

        for(String s: linhas){
            Venda v = parseLinhaVenda(s);
            res.add(v);
        }

        return res;
    }

    public static Set<Venda> parseFicheiroVendas(String fich){
        List<String> parsed = readLinesWithBuff(fich);
        Set<Venda> res = parseAllLinhasToSet(parsed);
        return res;
    }

}
