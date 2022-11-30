package com.matheusdoedev.todo.list.repositories;

import java.util.ArrayList;
import java.util.Date;

import com.matheusdoedev.todo.list.models.Task;
import com.matheusdoedev.todo.list.utils.Utils;
import com.matheusdoedev.todo.list.enums.TaskStatusEnum;

public class TaskRepository {

    private ArrayList<Task> tasks = new ArrayList();

    public TaskRepository() {
        // adiciona 7 tarefas para atender os requisitos da A3 
        for (int count = 0; count < 7; count++) {
            tasks.add(count, new Task(count, "Tem que fazer tal coisa"));
        }
    }

    public void create() {
        try {
            int index = this.tasks.size();
            String description = Utils.askForAString("Digite a descrição da tarefa: ");

            this.tasks.add(new Task(index, description));
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
            int taskId = Utils.askForAInt("Digite o id da tarefa: ");
            Task task = this.tasks.get(taskId);

            Utils.println(task.getId() + " | " + task.getDescription() + " | " + task.getStatus() + " | " + task.getCreatedAt() + " | " + task.getUpdatedAt());
        } catch (Exception error) {
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
