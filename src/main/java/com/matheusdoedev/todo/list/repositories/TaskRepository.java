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

    private ArrayList<NormalTask> normalTasks = new ArrayList();
    private ArrayList<RecurrentTask> recurrentTasks = new ArrayList();

    public TaskRepository() {
        // adiciona 7 tarefas para atender os requisitos da A3
        for (int count = 0; count < 7; count++) {
            this.normalTasks.add(count, new NormalTask(this.normalTasks.size(), "Tem que fazer tal coisa"));
        }
    }

    public void create() {
        try {
            String description = Utils.askForAString("Digite a descrição da tarefa: ");
            int newTaskId = this.normalTasks.size() + this.recurrentTasks.size();

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

                int selectedOption = 0;

                do {
                    selectedOption = Utils.getScanner().nextInt();
                } while (selectedOption < 1 || selectedOption > 4);

                this.recurrentTasks.add(this.recurrentTasks.size(),
                        new RecurrentTask(newTaskId + 1, description, TaskRecurrenceTypeEnum.WEEKDAYS));
            } else {
                this.normalTasks.add(this.normalTasks.size(), new NormalTask(newTaskId, description));
            }
            Utils.println("Tarefa criada com sucesso!");
        } catch (Exception error) {
            Utils.println(error.getMessage());
            Utils.println("Não foi possível criar a tarefa. Tente novamente!");
        }
    }

    public void update(int id) {
        try {
            String description = Utils.askForAString("Digite a nova descrição da tarefa: ");
            NormalTask task = this.normalTasks.get(id);

            task.setDescription(description);
            task.setUpdatedAt(new Date().getTime());
            this.normalTasks.set(id, task);
            Utils.println("Tarefa atualizada com sucesso.");
        } catch (Exception error) {
            Utils.println("Não foi possível atualizar a tarefa. Tente novamente!");
        }
    }

    public void show() {
        try {
            Utils.println("----------- Buscar tarefa ------------");
            int taskId = Utils.askForAInt("Digite o id da tarefa: ");
            int whatToDoWithTaskOption = 0;

            Utils.println("1) Editar tarefa");
            Utils.println("2) Deletar tarefa");
            Utils.println("3) Concluir tarefa");
            Utils.println("4) Voltar ao menu");
            Utils.println("Escolha uma opção:");
            while (whatToDoWithTaskOption < 1 || whatToDoWithTaskOption > 4) {
                whatToDoWithTaskOption = Utils.getScanner().nextInt();

                if (whatToDoWithTaskOption < 1 || whatToDoWithTaskOption > 4) {
                    Utils.println("Digite uma opção válida!");
                }
            }
            ;
            switch (whatToDoWithTaskOption) {
                case 1:
                    this.update(taskId);
                    break;
                case 2:
                    this.delete(taskId);
                    break;
                case 3:
                    this.completeTask(taskId);
                    break;
            }
        } catch (Exception error) {
            Utils.println("Erro ao mostrar detalhes da tarefa. Tente novamente!");
        }
    }

    public void delete(int id) {
        try {
            int option = 0;

            Utils.println("Tem certeza que deseja deletar a tarefa?");
            Utils.println("1) Sim");
            Utils.println("2) Não");
            while (option < 1 || option > 2) {
                option = Utils.getScanner().nextInt();

                if (option == 1) {
                    this.normalTasks.remove(id);
                }
                if (option < 1 || option > 2) {
                    Utils.println("Digite uma opção válida");
                }
            }
            Utils.println("Tarefa deletada com sucesso.");
        } catch (Exception error) {
            Utils.println("Erro ao tentar deletar tarefa. Tente novamente!");
        }
    }

    public void completeTask(int id) {
        try {
            NormalTask task = this.normalTasks.get(id);

            task.setStatus(TaskStatusEnum.DONE);
            this.normalTasks.set(id, task);
            Utils.println("Tarefa concluída com sucesso.");
        } catch (Exception error) {
            Utils.println("Erro ao tentar concluir tarefa. Tente novamente!");
        }
    }

    public void list() {
        try {
            Utils.println("------TAREFAS------");
            for (NormalTask task : this.normalTasks) {
                Utils.println(task.getId() + " | " + task.getDescription() + " | " + task.getStatus() + " | ");
            }
            for (RecurrentTask task : this.recurrentTasks) {
                Utils.println(task.getId() + " | " + task.getDescription() + " | " + task.getStatus() + " | "
                        + task.getRecurrenceType());
            }
        } catch (Exception error) {
            Utils.println("Não foi possível listar as tarefas.");
        }
    }
}
