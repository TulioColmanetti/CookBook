package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.Scanner;

public class NovaReceitaView {
    Scanner scanner;
    Receita receita;
    String nome;
    Categoria categoria;

    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
    }

    public String askNome() {
        System.out.println("Qual o nome da receita?");
        nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            askNome();
        }
        return nome;
    }

    public Categoria askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s\n", cat.ordinal(), cat.name());
        }
        int categoria = scanner.nextInt();
        if (categoria < 0 || categoria >= Categoria.values().length) {
            System.out.println("Categoria inválida!");
            askCategoria();
        }
        return Categoria.values()[categoria];
    }

    public int askTempoPreparo() {
        System.out.println("Qual o tempo de preparo da receita?");
        int tempoPreparo = scanner.nextInt();
        if (tempoPreparo <= 0) {
            System.out.println("Tempo de Preparo inválido!");
            askTempoPreparo();
        }
        return tempoPreparo;
    }

    public Rendimento askRendimento() {
        System.out.println("Qual o tipo de rendimento da receita?");
        for (TipoRendimento auxTipoRend : TipoRendimento.values()) {
            System.out.printf("%d - %s\n", auxTipoRend.ordinal(), auxTipoRend.name());
        }
        int tipoRend = scanner.nextInt();

        System.out.println("Qual a quantidade de rendimento da receita?");
        int qtdRend = scanner.nextInt();

        if (tipoRend < 0 || tipoRend >= TipoRendimento.values().length || qtdRend <= 0) {
            if (tipoRend < 0 || tipoRend >= TipoRendimento.values().length)
                System.out.println("Tipo de Rendimento inválida!");
            if (qtdRend <= 0)
                System.out.println("Quantidade de Rendimento inválida!");
            askRendimento();
        }

        return new Rendimento(qtdRend, TipoRendimento.values()[tipoRend]);
    }

    public Receita create() {
        this.nome = askNome();
        this.categoria = askCategoria();

        this.receita = new Receita(nome, categoria);

        this.receita.setTempoPreparo(askTempoPreparo());
        this.receita.setRendimento(askRendimento());

        return receita;
    }
}
