package com.letscode.cookBook.view;

import com.letscode.cookBook.controller.Catalogo;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.enums.Categoria;

import java.util.Scanner;

public class CatalogoView {
    private final Receita NONE_FOUND = new Receita("Nenhuma receita encontrada", Categoria.PRATO_UNICO);
    private Receita receita;
    private Catalogo controller = new Catalogo();
    private int curIndex = -1;

    private void showHeader() {
        ScreenUtil.printTextLine("", 80, true, '=');
        ScreenUtil.printTextLine("#### #### #### #  #  ###  #### #### #  #", 80, true, ' ');
        ScreenUtil.printTextLine("#    #  # #  # # #   #  # #  # #  # # # ", 80, true, ' ');
        ScreenUtil.printTextLine("#    #  # #  # ##    ###  #  # #  # ##  ", 80, true, ' ');
        ScreenUtil.printTextLine("#    #  # #  # # #   #  # #  # #  # # # ", 80, true, ' ');
        ScreenUtil.printTextLine("#### #### #### #  #  ###  #### #### #  #", 80, true, ' ');
        ScreenUtil.printTextLine("", 80, true, '=');
    }

    private void renderMenu() {
        ScreenUtil.clearScreen();
        showHeader();
        showReceita(receita == null ? NONE_FOUND : receita);
        ScreenUtil.printTextLine("", 80, true, '=');
        ScreenUtil.printTextLine("P: Receita anterior", 80, true);
        ScreenUtil.printTextLine("N: Receita seguinte", 80, true);
        ScreenUtil.printTextLine("+: Adicionar nova receita", 80, true);
        ScreenUtil.printTextLine("-: Remover receita", 80, true);
        ScreenUtil.printTextLine("S: Pesquisar receita", 80, true);
        ScreenUtil.printTextLine("", 80, true, '=');
        ScreenUtil.printTextLine("#: ", 80);
    }

    private void showReceita(Receita receita) {
        System.out.println(receita.toString());
    }

    private void showAnterior() {
        if (curIndex >= 0) {
            Receita retrievedReceita = controller.getReceita(curIndex - 1);
            if (retrievedReceita != null) {
                this.receita = retrievedReceita;
                curIndex--;
            }
        }
    }

    private void showSeguinte() {
        Receita retrievedReceita = controller.getReceita(curIndex + 1);
        if (retrievedReceita != null) {
            this.receita = retrievedReceita;
            curIndex++;
        }
    }

    private void add() {
        Receita createdReceita = new NovaReceitaView().create();
        if (createdReceita != null) {
            this.controller.add(createdReceita);
            this.receita = createdReceita;
            curIndex++;
        }
    }

    private void del() {
        if (curIndex >= 0) {
            controller.del(receita.getNome());
            this.receita = null;
            curIndex--;
        }
    }

    private void search() {
        ScreenUtil.printTextLine("Digite o nome da receita a ser pesquisada: ", 80);
        String searchName = new Scanner(System.in).next();
        this.receita = this.controller.getReceita(searchName);
        if (this.receita == null)
            curIndex = -1;
    }

    public void show() {
        String option;
        do {
            renderMenu();

            option = new Scanner(System.in).next();
            switch (option.toUpperCase()) {
                case "P":
                    showAnterior();
                    break;
                case "N":
                    showSeguinte();
                    break;
                case "+":
                    add();
                    break;
                case "-":
                    del();
                    break;
                case "S":
                    search();
                    break;
                default:
                    ScreenUtil.printTextLine("Opção inválida", 80);
                    ScreenUtil.printTextLine("#: ", 80);
            }
        } while (true);
    }
}
