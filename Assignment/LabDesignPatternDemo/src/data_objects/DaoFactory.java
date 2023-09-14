/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

/**
 *
 * @author lvhho
 */
public class DaoFactory implements IDaoFactory{

    @Override
    public IStudentDao studentDao() {
        return new StudentDao();
    }
    
}
