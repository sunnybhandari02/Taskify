import React, { createContext, useContext, useState, useEffect } from 'react';
import { useAuth } from './AuthContext';

export interface Task {
  id: string;
  title: string;
  description?: string;
  completed: boolean;
  status: 'pending' | 'in_progress' | 'completed';
  dueDate?: string;
  createdAt: string;
  updatedAt: string;
  userId: string;
}

interface TaskContextType {
  tasks: Task[];
  addTask: (task: Omit<Task, 'id' | 'createdAt' | 'updatedAt'>) => Promise<void>;
  updateTask: (id: string, updates: Partial<Task>) => Promise<void>;
  deleteTask: (id: string) => Promise<void>;
  toggleTask: (id: string) => Promise<void>;
  isLoading: boolean;
}

const TaskContext = createContext<TaskContextType | undefined>(undefined);

export const useTask = () => {
  const context = useContext(TaskContext);
  if (context === undefined) {
    throw new Error('useTask must be used within a TaskProvider');
  }
  return context;
};

export const TaskProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const { user } = useAuth();

  // Load tasks when user logs in
  useEffect(() => {
    if (user) {
      loadTasks();
    } else {
      setTasks([]);
    }
  }, [user]);

  const loadTasks = async () => {
    if (!user) return; // Ensure user is logged in

    setIsLoading(true);
    console.log("coming here in loadTasks");
    try {
      const response = await fetch(`http://localhost:8080/api/tasks/${user.id}`);
      if (!response.ok) {
        throw new Error('Failed to fetch tasks');
      }
      console.log(response);
      const data = await response.json();
      setTasks(data);
      if (user) {
      localStorage.setItem(`tasks_${user.id}`, JSON.stringify(data));
    }
      console.log(data);
    } catch (error) {
      console.error('Failed to load tasks:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const saveTasks = (updatedTasks: Task[]) => {
    setTasks(updatedTasks);
    if (user) {
      localStorage.setItem(`tasks_${user.id}`, JSON.stringify(updatedTasks));
    }
  };

  const addTask = async (newTask: Omit<Task, 'id' | 'createdAt' | 'updatedAt'>) => {
    setIsLoading(true);
    try {
      if(!user) {
        throw new Error("User not logged in");
      }

      console.log(newTask);
      console.log(user);
      const response = await fetch('http://localhost:8080/api/tasks', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          title: newTask.title,
          description: newTask.description,
          status: newTask.status,
          dueDate: newTask.dueDate,
          userId: user.id,
        }),
      });

      console.log(response);
      if (!response.ok) {
        throw new Error('Failed to create task');
      }
    
      const createdTask = await response.json();

      const updatedTasks = [...tasks, createdTask];
      saveTasks(updatedTasks);
    } catch (error) {
      console.error('Failed to add task:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const updateTask = async (id: string, updates: Partial<Task>) => {
    setIsLoading(true);
    console.log(updates.status);
    try {
      const response = await fetch(`http://localhost:8080/api/tasks/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        ...updates,
        status: updates.status?.toUpperCase(),
      }),
    });
    if (!response.ok) {
      throw new Error('Failed to update task');
    }

    const updatedTask = await response.json();

    const updatedTasks = tasks.map(task =>
      task.id === id ? updatedTask : task
    );

    saveTasks(updatedTasks);
    } catch (error) {
      console.error('Failed to update task:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const deleteTask = async (id: string) => {
    setIsLoading(true);
    try {
      setIsLoading(true);
      const response = await fetch(`http://localhost:8080/api/tasks/${id}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error('Failed to delete task');
      }

      const updatedTasks = tasks.filter(task => task.id !== id);
      saveTasks(updatedTasks);
    } catch (error) {
      console.error('Failed to delete task:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const toggleTask = async (id: string) => {
    const task = tasks.find(t => t.id === id);
    if (!task || !user) return;
    const updatedStatus = task.status.toLowerCase() === 'completed' ? 'pending' : 'completed';  
    await updateTask(id, { 
      ...task,
      status: updatedStatus,
      userId: user.id,
    });
  };

  return (
    <TaskContext.Provider value={{
      tasks,
      addTask,
      updateTask,
      deleteTask,
      toggleTask,
      isLoading
    }}>
      {children}
    </TaskContext.Provider>
  );
};