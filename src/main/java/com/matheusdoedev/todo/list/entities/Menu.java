package com.matheusdoedev.todo.list.entities;

import com.matheusdoedev.todo.list.utils.Utils;
import com.matheusdoedev.todo.list.repositories.TaskRepository;

public class Menu {

    private int LAST_MENU_OPTION = 4;
    private int selectedMenuOption = 999;
    private TaskRepository taskRepository;
    private String[] MESSAGES = {
        "Selecione uma opção do menu:",
        "1) Criar uma tarefa",
        "2) Buscar tarefa",
        "3) Listar todas as tarefas",
        "4) Sair"
    };

    public Menu() {
        taskRepository = new TaskRepository();
    }

    public void start() {
        this.printMenuWelcomeMessage();
        this.askForAMenuOption();
    }

    private void askForAMenuOption() {
        try {
            this.printMenuOptionsMessage();
            this.selectedMenuOption = Utils.askForAInt("Selecione uma opção: ");

            if (this.selectedMenuOption != this.LAST_MENU_OPTION) {
                this.handleSwitchMenuOptions(this.selectedMenuOption);
                this.handleContinue();
            }
        } catch (Exception error) {
            Utils.println("Opção inválida. Digite uma opção válida de 1 à 4.");
            this.handleContinue();
        }
    }

    private void handleSwitchMenuOptions(int option) {
        if (this.selectedMenuOption < 1 || this.selectedMenuOption > this.LAST_MENU_OPTION) {
            Utils.println("Digite uma opção válida de 1 à " + this.LAST_MENU_OPTION);
            this.askForAMenuOption();
        }

        switch (option) {
            case 1:
                this.taskRepository.create();
                break;
            case 2:
                this.taskRepository.show();
                break;
            case 3:
                this.taskRepository.list();
                break;
            default:
                break;
        }
    }

    private void handleContinue() {
        Utils.println("Deseja continuar?");
        Utils.println("1) Voltar para o menu");
        Utils.println("2) Sair");

        int continueOption;

        do {
            continueOption = Utils.getScanner().nextInt();

            if (continueOption != 1 && continueOption != 2) {
                Utils.println("Selecione uma opção válida de 1 à 2.");
            }
            if (continueOption == 1) {
                this.askForAMenuOption();
            }
        } while (continueOption != 1 && continueOption != 2);
    }

    private void printMenuWelcomeMessage() {
        Utils.println("Bem vindo ao TODOLIST API!");
    }

    private void printMenuOptionsMessage() {
        for (String message : this.MESSAGES) {
            Utils.println(message);
        }
    }
}