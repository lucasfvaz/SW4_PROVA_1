/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Comparator;

/**
 *
 * @author ertel
 */
public class ComparaNota implements Comparator<ProvasFeita>{
     @Override
    public int compare(ProvasFeita prova1, ProvasFeita prova2) {
        return Double.compare(prova2.getNota(), prova1.getNota());
    }
}
