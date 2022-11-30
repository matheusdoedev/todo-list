package com.matheusdoedev.todo.list.models;

import com.matheusdoedev.todo.list.utils.Utils;
import com.matheusdoedev.todo.list.repositories.TaskRepository;

public class Menu {

    private int LAST_MENU_OPTION = 7;
    private int selectedMenuOption = 999;
    private TaskRepository taskRepository;
    private String[] MESSAGES = {
        "Selecione uma opção do menu:",
        "1) Criar uma tarefa",
        "2) Editar uma tarefa",
        "3) Buscar tarefa pelo id",
        "4) Listar todas as tarefas",
        "5) Deletar tarefa pelo id",
        "6) Concluir tarefa",
        "7) Sair"
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
            Utils.println("Opção inválida. Digite uma opção válida de 1 à 7.");
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
                this.taskRepository.update();
                break;
            case 3:
                this.taskRepository.show();
                break;
            case 4:
                this.taskRepository.list();
                break;
            case 5:
                this.taskRepository.delete();
                break;
            case 6:
                this.taskRepository.completeTask();
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
