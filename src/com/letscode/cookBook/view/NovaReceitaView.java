package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Ingrediente;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoMedida;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.Scanner;

public class NovaReceitaView {
    Scanner scanner;
    Receita receita;
    String nome;
    Categoria categoria;
    TipoRendimento tipoRendimento;
    int qtdRendimento;

    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
    }

    public void askNome() {
        System.out.println("Qual o nome da receita?");
        this.nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            askNome();
        }
    }

    public void askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s\n", cat.ordinal(), cat.name());
        }
        int catIndex = scanner.nextInt();
        if (catIndex < 0 || catIndex >= Categoria.values().length) {
            System.out.println("Categoria inválida!");
            askCategoria();
        } else
            this.categoria = Categoria.values()[catIndex];
    }

    public void askTempoPreparo() {
        System.out.println("Qual o tempo de preparo da receita?");
        int tempoPreparo = scanner.nextInt();
        if (tempoPreparo <= 0) {
            System.out.println("Tempo de preparo inválido!");
            askTempoPreparo();
        } else
            this.receita.setTempoPreparo(tempoPreparo);
    }

    private void askTipoRendimento() {
        System.out.println("Qual o tipo de rendimento da receita?");
        for (TipoRendimento auxTipoRend : TipoRendimento.values()) {
            System.out.printf("%d - %s\n", auxTipoRend.ordinal(), auxTipoRend.name());
        }
        int tipoRend = scanner.nextInt();

        if (tipoRend < 0 || tipoRend >= TipoRendimento.values().length) {
            System.out.println("Tipo de rendimento inválida!");
            askTipoRendimento();
        } else
            this.tipoRendimento = TipoRendimento.values()[tipoRend];
    }

    private void askQtdRendimento(){
        System.out.println("Qual a quantidade de rendimento da receita?");
        int qtdRend = scanner.nextInt();

        if (qtdRend <= 0) {
            System.out.println("Quantidade de rendimento inválida!");
            askQtdRendimento();
        } else
            this.qtdRendimento = qtdRend;
    }

    public void askRendimento() {
        askTipoRendimento();
        askQtdRendimento();
        this.receita.setRendimento(new Rendimento(this.qtdRendimento, this.tipoRendimento));
    }

    public void askIngredientes() {
        System.out.println("Quantos ingredientes a receita possui?");
        int qtdIngredientes = scanner.nextInt();
        if (qtdIngredientes <= 0) {
            System.out.println("Quantidade de ingredientes inválida!");
            askIngredientes();
        } else {
            Ingrediente[] ingredientes = new Ingrediente[qtdIngredientes];

            for (int i = 0; i < qtdIngredientes; i++) {
                String ingredNome = "";
                do {
                    System.out.printf("Qual o nome do ingrediente %d?\n", i+1);
                    ingredNome = scanner.next();

                    if (ingredNome.isBlank())
                        System.out.println("Nome do ingrediente inválido!");
                } while (ingredNome.isBlank());

                int ingredTipoMedidaIndex = -1;
                do {
                    System.out.printf("Qual o tipo de medida do ingrediente %d?\n", i+1);
                    for (TipoMedida med : TipoMedida.values()) {
                        System.out.printf("%d - %s\n", med.ordinal(), med.name());
                    }
                    ingredTipoMedidaIndex = scanner.nextInt();

                    if (ingredTipoMedidaIndex < 0 || ingredTipoMedidaIndex >= TipoMedida.values().length)
                        System.out.println("Tipo de medida do ingrediente inválida!");
                } while (ingredTipoMedidaIndex < 0 || ingredTipoMedidaIndex >= TipoMedida.values().length);

                double ingredQtd = -1;
                do {
                    System.out.printf("Qual a quantidade do ingrediente %d?\n", i+1);
                    ingredQtd = scanner.nextDouble();

                    if (ingredQtd <= 0)
                        System.out.println("Quantidade do ingrediente inválida!");
                } while (ingredQtd <= 0);

                ingredientes[i] = new Ingrediente(ingredNome, ingredQtd, TipoMedida.values()[ingredTipoMedidaIndex]);
            }

            this.receita.setIngredientes(ingredientes);
        }
    }

    public void askModoPreparo() {
        System.out.println("Quantas etapas o modo de preparo da receita possui?");
        int qtdEtapas = scanner.nextInt();
        if (qtdEtapas <= 0) {
            System.out.println("Quantidade de etapas inválida!");
            askIngredientes();
        } else {
            String[] etapasModoDePreparo = new String[qtdEtapas];

            for (int i = 0; i < qtdEtapas; i++) {
                do {
                    System.out.printf("Qual a etapa número %d do modo de preparo?\n", i+1);
                    etapasModoDePreparo[i] = scanner.next();

                    if (etapasModoDePreparo[i].isBlank())
                        System.out.println("Etapa do modo de preparo inválida!");
                } while (etapasModoDePreparo[i].isBlank());
            }

            this.receita.setModoPreparo(etapasModoDePreparo);
        }
    }

    public Receita create() {

        askNome();
        askCategoria();

        this.receita = new Receita(nome, categoria);

        askTempoPreparo();
        askRendimento();
        askIngredientes();
        askModoPreparo();

        return receita;
    }
}
