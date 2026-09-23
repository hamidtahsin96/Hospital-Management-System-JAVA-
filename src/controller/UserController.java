package controller;
import model.*;

public class UserController
{
    public void insertUser(User u)
    {
        User users[] = this.getAllUser();

        for(int i = 0; i < users.length; i++)
        {
            if(users[i] == null)
            {
                users[i] = u;
                break;
            }
        }

        this.write(users);
    }

    public void updateUser(User u)
    {
        User users[] = this.getAllUser();

        for(int i = 0; i < users.length; i++)
        {
            if(users[i] != null)
            {
                if(users[i].getUserId().equals(u.getUserId()))
                {
                    users[i] = u;
                    break;
                }
            }
        }

        this.write(users);
    }

    public void deleteUser(String userId)
    {
        User users[] = this.getAllUser();

        for(int i = 0; i < users.length; i++)
        {
            if(users[i] != null)
            {
                if(users[i].getUserId().equals(userId))
                {
                    users[i] = null;
                    break;
                }
            }
        }

        this.write(users);
    }

    public User searchUser(String userId)
    {
        User users[] = this.getAllUser();

        for(int i = 0; i < users.length; i++)
        {
            if(users[i] != null)
            {
                if(users[i].getUserId().equals(userId))
                {
                    return users[i];
                }
            }
        }

        return null;
    }

    public User[] getAllUser()
    {
        String fileName = "Information/users.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        User users[] = new User[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                User u = new User();
                users[i] = u.formUser(values[i]);
            }
        }

        return users;
    }

    public void write(User users[])
    {
        String information[] = new String[100];

        for(int i = 0; i < users.length; i++)
        {
            if(users[i] != null)
            {
                information[i] = users[i].toStringUser();
            }
        }

        String fileName = "Information/users.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}