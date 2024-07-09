import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public Principal() throws IOException {
    }


    public static void main(String[] args) throws IOException, InterruptedException {



        Scanner leitura = new Scanner(System.in);
        ConsultaMoedas consultas = new ConsultaMoedas();
//        ArrayList<ConversorMoedas> listaDeConversoes = new ArrayList<>();




        while (true) {
            System.out.println("""
                    ********** BEM VINDO AO CONVERSOR DE MOEDAS **********
                              
                      De real brasileiro para: 
                      
                      1.Dólar Americano - "USD"
                      2.Euro - "EUR"    
                      3.Libra - "GBP"
                      4.Iene - "JPY"
                      5.Franco Suíço - "CHF"
                      6.Sair.
                               
                      Escolha a opção que representa a moeda que deseja converter.""");

            int escolha = leitura.nextInt();
            if (escolha == 0)
                break;

            switch (escolha){
                case 1:
                    conversorMoedas(consultas,"USD");
                    break;
                case 2:
                    conversorMoedas(consultas,"EUR");
                    break;
                case 3:
                    conversorMoedas(consultas,"GBP");
                    break;
                case 4:
                    conversorMoedas(consultas,"JPY");
                    break;
                case 5:
                    conversorMoedas(consultas,"CHF");
                    break;
                case 6:
                    System.out.println("Obrigada por converter conosco, programa encerrado.");
                    break;
                default:
                    System.out.println("Opção incorreta.");
            }


        }




    }

    public static void conversorMoedas(ConsultaMoedas consulta,String moedaDestino) throws IOException {


        MoedasAPI consultaMoeda = consulta.consultarMoeda("BRL");

//        //Criação da lista de consultas.
//        ArrayList<ConsultaMoedas> listaDeConsultasRealizadas = new ArrayList<>();





        if (consultaMoeda != null && consultaMoeda.conversionRates() != null){
            Double taxaMoeda = (Double) consultaMoeda.conversionRates().get(moedaDestino);

              if (taxaMoeda != null){
                  double moedaReal;
                  Scanner segundaLeitura = new Scanner(System.in);

                  System.out.println("Digite o valor que deseja converter ");
                  moedaReal = segundaLeitura.nextDouble();

                  double moedaConvertida = moedaReal * taxaMoeda;
                  System.out.println("O valor inserido ( R$ "+ moedaReal+") em BRL "+ "agora convertido em "+ moedaDestino + " no novo valor de " + moedaConvertida);

//                  listaDeConsultasRealizadas.add(consulta);
//
//                  //Variáveis que serão usadas para o gerador de arquivo em Json.
//                  LocalDateTime data = LocalDateTime.now();
//                  String formatacaoDataHora = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss"));
//                  String mensagemGerador = """
//                   Segue o registro das conversões.
//
//
//                   Obrigada por converter suas moedas conosco!
//                    """.formatted(formatacaoDataHora);
//
//                  //Registro das consultas em arquivo Json
//                  Gson gson = new GsonBuilder().setPrettyPrinting().create(); //trazendo os dados do Json e formatando
//                  FileWriter conversoes = new FileWriter("consultasRealizadas.json");
//                  conversoes.write(mensagemGerador);
//                  conversoes.write(gson.toJson(listaDeConsultasRealizadas.toString()));
//                  conversoes.write("Desenvolvido por Bárbara Santana Braz.");
//                  conversoes.close();


              } else{
                  System.out.println("Taxa para " + moedaDestino + "não está disponível. ");
              }
        }
    }






}
