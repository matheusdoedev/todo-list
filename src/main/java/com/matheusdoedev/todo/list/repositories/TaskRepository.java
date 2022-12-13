package com.matheusdoedev.todo.list.repositories;

import java.util.ArrayList;
import java.util.Date;

import com.matheusdoedev.todo.list.models.Task;
import com.matheusdoedev.todo.list.entities.NormalTask;
import com.matheusdoedev.todo.list.entities.RecurrentTask;
import com.matheusdoedev.todo.list.enums.TaskRecurrenceTypeEnum;
import com.matheusdoedev.todo.list.utils.Utils;
import com.matheusdoedev.todo.list.enums.TaskStatusEnum;

public class TaskRepository {

    private ArrayList<Task> tasks = new ArrayList();

    public TaskRepository() {
        // adiciona 7 tarefas para atender os requisitos da A3
        for (int count = 0; count < 7; count++) {
            tasks.add(count, new NormalTask("Tem que fazer tal coisa"));
        }
    }

    public void create() {
        try {
            String description = Utils.askForAString("Digite a descrição da tarefa: ");

            Utils.println("É uma tarefa recorrente? (Digite o número da opção)");
            Utils.println("1) Sim");
            Utils.println("2) Não");

            int isRecurrent;

            do {
                isRecurrent = Utils.getScanner().nextInt();

                if (isRecurrent < 1 || isRecurrent > 2) {
                    Utils.println("Digite uma opção válida.");
                }
            } while (isRecurrent < 1 || isRecurrent > 2);

            if (isRecurrent == 1) {
                Utils.println("Qual é a frequência da recorrência?");
                Utils.println("1) Dias da semana (Seg-Sex)");
                Utils.println("2) Semanal");
                Utils.println("3) Todos os dias");
                Utils.println("4) Mensal");

                int selectedOption;

                do {
                    selectedOption = Utils.getScanner().nextInt();
                } while (selectedOption < 1 || selectedOption > 4);

                this.tasks.add(new RecurrentTask(description, TaskRecurrenceTypeEnum.WEEKDAYS));
            } else {
                this.tasks.add(new NormalTask(description));
            }
            Utils.println("Tarefa criada com sucesso!");
        } catch (Exception error) {
            Utils.println("Não foi possível criar a tarefa. Tente novamente!");
        }
    }

    public void update() {
        try {
            int taskId = Utils.askForAInt("Digite o id da tarefa: ");
            String description = Utils.askForAString("Digite a nova descrição da tarefa: ");
            Task task = this.tasks.get(taskId);

            task.setDescription(description);
            task.setUpdatedAt(new Date().getTime());
            this.tasks.set(taskId, task);
            Utils.println("Tarefa atualizada com sucesso.");
        } catch (Exception error) {
            Utils.println("Não foi possível atualizar a tarefa. Tente novamente!");
        }
    }

    public void show() {
        try {
            Utils.println("----------- Buscar tarefa ------------");
            String searchDescription = Utils.askForAString("Digite uma parte da descrição da tarefa: ");
            ArrayList<Task> tasksFound = new ArrayList();
            Task selectedTask;

            for (Task task : this.tasks) {
                if (task.getDescription().contains(searchDescription)) {
                    tasksFound.add(task.getId(), task);
                }
            }
            if (tasksFound.length > 1) {
                int option = 0;

                for (int index = 1; index <= tasksFound.length; index++) {
                    Task task = tasksFound[index];

                    Utils.println("Id: " + task.getId() + "| Descrição: " + task.getDescription());
                }
                Utils.askForAString("Escolha uma tarefa de 1 à " + tasksFound.length);
                while (option < 1 || option > tasksFound.length) {
                    option = Utils.getScanner().nextInt();

                    if (option < 1 || option > tasksFound.length) {
                        Utils.println("Digite uma opção válida!");
                    }
                }
                selectedTask = tasksFound[option];
            } else {
                selectedTask = tasksFound[0];
            }

            int whatToDoWithTaskOption = 0;

            Utils.println("1) Editar tarefa");
            Utils.println("2) Deletar tarefa");
            Utils.println("3) Voltar ao menu");
            Utils.println("Escolha uma opção:");
            while (whatToDoWithTaskOption < 1 || whatToDoWithTaskOption > 3) {
                whatToDoWithTaskOption = Utils.getScanner().nextInt();

                if (whatToDoWithTaskOption < 1 || whatToDoWithTaskOption > 3) {
                    Utils.println("Digite uma opção válida!");
                }
            }
            ;
            switch (whatToDoWithTaskOption) {
                case 1:
                    this.update(selectedTask.getId());
                    break;
                case 2:
                    this.delete(selectedTask.getId());
                    break;

            tch (Exception error) {
            Utils.println("Erro ao mostrar detalhes da tarefa. Tente novamente!");
        }
    }

    public void delete() {
        try {
            int taskId = Utils.askForAInt("Digite o id da tarefa: ");

            this.tasks.remove(taskId);
            Utils.println("Tarefa deletada com sucesso.");
        } catch (Exception error) {
            Utils.println("Erro ao tentar deletar tarefa. Tente novamente!");
        }
    }

    public void completeTask() {
        try {
            int taskId = Utils.askForAInt("Digite o id da tarefa: ");
            Task task = this.tasks.get(taskId);

            task.setStatus(TaskStatusEnum.DONE);
            Utils.println("Tarefa concluída com sucesso.");
        } catch (Exception error) {
            Utils.println("Erro ao tentar concluir tarefa. Tente novamente!");
        }
    }

    public void list() {
        try {
            Utils.println("------TAREFAS------");
            for (Task task : this.tasks) {
                Utils.println(task.getId() + " | " + task.getDescription() + " | " + task.getStatus());
            }
        } catch (Exception error) {
            Utils.println("Não foi possível listar as tarefas.");
        }
    }
}