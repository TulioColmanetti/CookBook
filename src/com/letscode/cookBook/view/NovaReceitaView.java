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
        System.out.println("Quais os ingredientes da receita?");
        Ingrediente[] ingredientes = new Ingrediente[1];
        ingredientes[0] = new Ingrediente("IngredienteTeste", 10, TipoMedida.values()[0]);
        this.receita.setIngredientes(ingredientes);
    }

    public void askModoPreparo() {
        System.out.println("Qual o modo de preparo da receita?");
        String[] modoDePreparo = new String[1];
        modoDePreparo[0] = "Modo de preparo 1";
        this.receita.setModoPreparo(modoDePreparo);
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
