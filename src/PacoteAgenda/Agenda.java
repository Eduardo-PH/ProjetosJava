package PacoteAgenda;
// Eduardo Philipe - 202051621966
import java.sql.*;
import java.util.Scanner;

public class Agenda {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/agenda_telefonica";
            String user = "root";
            String pwd = "";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pwd);

            System.out.println("Conectou com o Banco de Dados!");
            String nome;
            int menu, n1, n2;
            do{
                System.out.println("========= AGENDA =========");
                System.out.println("1 - INSERIR");
                System.out.println("2 - DELETAR");
                System.out.println("3 - ATUALIZAR");
                System.out.println("4 - LISTAR");
                System.out.println("5 - SAIR");
                System.out.print("===> Escolha uma opção: ");
                menu = entrada.nextInt();
                String teste = entrada.nextLine();

                switch (menu){
                    case 1:
                        PreparedStatement stat1 = con.prepareStatement("INSERT INTO `contatos` (`nome`, `numero1`, `numero2`) VALUES (?, ?, ?)");

                        System.out.print("Ditgite um nome ");
                        nome = entrada.nextLine();
                        System.out.print("Digite um numero (APENAS 8 DIGITOS!) : ");
                        n1 = entrada.nextInt();
                        System.out.print("Digite outro numero (APENAS 8 DIGITOS!) : ");
                        n2 = entrada.nextInt();

                        stat1.setString(1, nome);
                        stat1.setInt(2, n1);
                        stat1.setInt(3, n2);
                        stat1.execute();

                        System.out.println("Dados inseridos!");
                        stat1.close();
                        break;
                    case 2:
                        System.out.print("Ditgite o nome do contato que deseja deletar: ");
                        nome = entrada.nextLine();

                        PreparedStatement stat2 = con.prepareStatement("DELETE FROM `contatos` WHERE `contatos`.`nome` = \'" + nome + "\'");
                        stat2.execute();

                        System.out.println("Contato Deletado!");
                        stat2.close();
                        break;
                    case 3:
                        System.out.print("Ditgite o nome do contato que deseja atualizar: ");
                        nome = entrada.nextLine();
                        System.out.print("Digite o numero que deseja atualizar (APENAS 8 DIGITOS!) : ");
                        n1 = entrada.nextInt();
                        System.out.print("Digite outro numero que deseja atualizar (APENAS 8 DIGITOS!) : ");
                        n2 = entrada.nextInt();

                        PreparedStatement stat3 = con.prepareStatement("UPDATE `contatos` SET `numero1` = '" + n1 + "', `numero2` = '" + n2 + "' WHERE `contatos`.`nome` = '" + nome + "'" );
                        stat3.execute();

                        System.out.println("Contato Atualizado!");
                        stat3.close();
                        break;
                    case 4:
                        System.out.println("==============================");
                        PreparedStatement stat = con.prepareStatement("SELECT * FROM `contatos` WHERE 1");
                        ResultSet rs = stat.executeQuery();
                        while(rs.next()){
                            System.out.print(rs.getString(1) + " - ");
                            System.out.print(rs.getString(2) + " - ");
                            System.out.println(rs.getString(3));
                        }
                        System.out.println("==============================");
                        stat.close();
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção de menu invalida!");
                }

            }while(menu != 5);

            entrada.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Exceção de SQL - abrir conexão!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Não encontrou a classe do driver!");
            e.printStackTrace();
        }
    }
}
