package conta;

import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;
import conta.util.Cores;
import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;


public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ContaController contas = new ContaController();
				

				Scanner leia = new Scanner(System.in);

				int opcao, numero, agencia, tipo, aniversario, numeroDestino;
				String titular;
				float saldo, limite, valor;

			

				while (true) {

					System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
							+ "*****************************************************");
					System.out.println("                                                     ");
					System.out.println("                BANCO DO BRAZIL COM Z                ");
					System.out.println("                                                     ");
					System.out.println("*****************************************************");
					System.out.println("                                                     ");
					System.out.println("            1 - Criar Conta                          ");
					System.out.println("            2 - Listar todas as Contas               ");
					System.out.println("            3 - Buscar Conta por Numero              ");
					System.out.println("            4 - Atualizar Dados da Conta             ");
					System.out.println("            5 - Apagar Conta                         ");
					System.out.println("            6 - Sacar                                ");
					System.out.println("            7 - Depositar                            ");
					System.out.println("            8 - Transferir valores entre Contas      ");
					System.out.println("            9 - Sair                                 ");
					System.out.println("                                                     ");
					System.out.println("*****************************************************");
					System.out.println("Entre com a opção desejada:                          ");
					System.out.println("                                                     " + Cores.TEXT_RESET);
					
					try {
						opcao = leia.nextInt();
					}catch(InputMismatchException e){
						System.out.println("\nDigite valores inteiros!");
						leia.nextLine();
						opcao=0;
					}

					if (opcao == 9) {
						System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
						leia.close();
						System.exit(0);
					}

					switch (opcao) {
					case 1:
						System.out.println(Cores.TEXT_WHITE+" Criar Conta\n\n");
						
						System.out.println("Digite o número da agencia:  ");
						agencia = leia.nextInt();
						System.out.println("Digite o nome do titular:  ");
						leia.skip("\\R?");
						titular= leia.nextLine();
						
						do {
							System.out.println("Digite o tipo da conta (1-CC ou 2-CP):  ");
							tipo= leia.nextInt();
							
						}while(tipo < 1 && tipo >2);
						System.out.println("Digite o saldo da conta (R$):  ");
						saldo = leia.nextFloat();
						
						switch(tipo) {
						case 1 ->{
							System.out.println("Digite o limite de crédito (R$):  ");
							limite = leia.nextFloat();
							contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
							}
						case 2 ->{
							System.out.println("Digite o dia do aniversario da conta:  ");
							aniversario = leia.nextInt();
							contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
						}
						}
						

						keyPress();
						break;
					case 2:
						System.out.println(Cores.TEXT_WHITE+" Listar todas as Contas\n\n");
                        contas.listarTodas();
						keyPress();
						break;
					case 3:
						System.out.println(Cores.TEXT_WHITE+" Buscar Conta por número\n\n");
                             
						System.out.println("Digite o número da conta: ");
						numero = leia.nextInt();
						
						contas.procurarPorNumero(numero);
						
						keyPress();
						break;
					case 4:
						System.out.println(Cores.TEXT_WHITE+" Atualizar dados da Conta\n\n");
						
						System.out.println("Digite o número da conta: ");
						numero = leia.nextInt();
						
						if (contas.buscarNaCollection(numero) != null) {
							System.out.println("Digite o número da Agência: ");
							agencia = leia.nextInt();
							System.out.println("Digite o nome do titular:  ");
							leia.skip("\\R?");
							titular= leia.nextLine();
							System.out.println("Digite o saldo da conta (R$):  ");
							saldo = leia.nextFloat();
							
							tipo = contas.retornaTipo(numero);
							
							switch(tipo) {
							case 1 ->{
								System.out.println("Digite o limite de crédito (R$):  ");
								limite = leia.nextFloat();
								contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
								}
							case 2 ->{
								System.out.println("Digite o dia do aniversario da conta:  ");
								aniversario = leia.nextInt();
								contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
			
							}
							default -> {
								System.out.println("\n Tipo de conta inválido!");
							}
						    }
						}	else 
							System.out.println("\n Conta não encontrada!");
						

						keyPress();
						break;
					case 5:
						System.out.println(Cores.TEXT_WHITE+" Apagar Conta\n\n");
						
						System.out.println("Digite o número da conta: ");
						numero = leia.nextInt();
						
						contas.deletar(numero);
						

						keyPress();
						break;
					case 6:
						System.out.println(Cores.TEXT_WHITE+" Sacar\n\n");
						
						System.out.println("Digite o numero da conta: ");
						numero = leia.nextInt();
						
						do {
							System.out.println("Digite o valor do saque (R$): ");
							valor = leia.nextFloat();
						
						}while(valor <=0);
						contas.sacar(numero, valor);

						keyPress();
						break;
					case 7:
						System.out.println(Cores.TEXT_WHITE+" Depositar\n\n");
						System.out.println("Digite o numero da conta: ");
						numero = leia.nextInt();
						
						do {
							System.out.println("Digite o valor do depósito (R$): ");
							valor = leia.nextFloat();
						
						}while(valor <=0);
						contas.depositar(numero, valor);
						
						keyPress();
						break;
					case 8:
						System.out.println(Cores.TEXT_WHITE+" Transferir\n\n");
						System.out.println("Digite o numero da conta de origem: ");
						numero = leia.nextInt();
						System.out.println("Digite o numero da conta de destino: ");
						numeroDestino = leia.nextInt();
						
						do {
							System.out.println("Digite o valor da transferência (R$): ");
							valor = leia.nextFloat();
						
						}while(valor <=0);
						contas.transferir(numero, numeroDestino, valor);
						

						keyPress();
						break;
					default:
						System.out.println("\nOpção Inválida");
						
						keyPress();
						break;
					}
				}
			}

			public static void keyPress() {

		        try {
		            System.out.println("\n\nPressione Enter para Continuar...");
		            System.in.read();
		        } catch (IOException e) {
		            System.out.println("Você pressionou uma tecla diferente de enter!");
		        }
			}
		}
