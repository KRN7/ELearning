package br.com.elearning.praticas.teste;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.SimuladoUsuario;
import br.com.elearning.praticas.util.*;
import java.util.List;
import java.util.logging.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe
 */
public class Test {

    private static Facade facade;

    public static void main(String[] w) {

        facade = new Facade();

        try {
            if (ConnectionFactory.getConnection() != null) {
                System.out.println("conexao ok\n");
                facade = new Facade();
            }

            if (ConnectionFactory.getConnection() == null) {
                System.out.println("conexao falhou\n");
                facade = new Facade();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
