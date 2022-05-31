package com.dh.h2;

import java.sql.*;

public class Test {
        private static final String SQL_TABLE_CREATE = "DROP TABLE IF EXISTS FIGURA; CREATE TABLE FIGURA" //Creamos tabla. Si existe la eliminamos
            +"(" //Campos de la tabla
            +"ID INT PRIMARY KEY,"
            +"NOMBRE varchar(100) NOT NULL,"
            +"COLOR varchar(100) NOT NULL"
                +")";

        private static final String SQL_INSERT = "INSERT INTO FIGURA (ID, NOMBRE, COLOR) VALUES(?,?,?)"; //Insertamos una figura c/ los campos marcados y sus valores que luego insertamos


        public static void main(String[] args) throws Exception {
            Figura circulo1 = new Figura("circulo", "rojo"); //Instanciando las figuras
            Figura circulo2 = new Figura("circulo", "rojo");
            Figura cuadrado1 = new Figura("cuadrado", "azul");
            Figura cuadrado2 = new Figura("cuadrado", "verde");
            Figura cuadrado3 = new Figura("cuadrado", "violeta");


            //Para poder guardar en la base de datos primero:
            Connection connection = null; //La conexión la igualo a nulo

            try {
                connection = getConnection(); //Creamos conexión
                Statement statement = connection.createStatement(); //Creamos statement
                statement.execute(SQL_TABLE_CREATE);//Lo primero que ejecutamos es crear la tabla

                PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);

                //Empiezo a insertar en la base de datos (En este caso, cada figura c/ sus propiedades)
                psInsert.setInt(1,1);
                psInsert.setString(2, circulo1.getNombre());
                psInsert.setString(3, circulo1.getColor());
                psInsert.execute();//Ahora ejecutamos el insert

                psInsert.setInt(1, 2);
                psInsert.setString(2, circulo2.getNombre());
                psInsert.setString(3, circulo2.getColor());
                psInsert.execute();//Ahora ejecutamos el insert

                psInsert.setInt(1, 3);
                psInsert.setString(2, cuadrado1.getNombre());
                psInsert.setString(3, cuadrado1.getColor());
                psInsert.execute();//Ahora ejecutamos el insert

                psInsert.setInt(1, 4);
                psInsert.setString(2, cuadrado2.getNombre());
                psInsert.setString(3, cuadrado2.getColor());
                psInsert.execute();//Ahora ejecutamos el insert

                psInsert.setInt(1, 5);
                psInsert.setString(2, cuadrado3.getNombre());
                psInsert.setString(3, cuadrado3.getColor());
                psInsert.execute(); //Ahora ejecutamos el insert

                //Empezar la transacción (Tx)
                connection.setAutoCommit(false);


                connection.commit();

                connection.setAutoCommit(true);

                String sql= "SELECT * FROM FIGURA WHERE color LIKE 'rojo' AND nombre LIKE'circulo' "; //Corremos una query para corroborar (en este caso si hay círculos rojos)
                Statement stmt = connection.createStatement();
                ResultSet rd = stmt.executeQuery(sql); //ResultSet para buscar en la base de datos (Esto nos devuelve un objeto)
                while(rd.next()){ //Iteramos el objeto devuelto, siguiendo el valor next
                    System.out.println(rd.getInt(1) + rd.getString(2)+ rd.getString(3));

                }

            }catch (Exception e){
                e.printStackTrace(); //Imprime por pantalla la exception
                connection.rollback(); //Que vuelve para atrás todos los valores incertados

            }finally {
                connection.close(); //Buena práctica cerrar la conexión

            }
        }

        private static Connection getConnection() throws Exception{//Creamos una conexión, siempre usamos el mismo driver
            Class.forName("org.h2.Driver").newInstance();
            return DriverManager.getConnection("jdbc:h2:" + "~/db_figuras", "sa", "" );
        }


    }


