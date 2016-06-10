package javaapplication1;

import dao.Persistencia;
import java.util.List;
import model.Cesta;
import model.Produto;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.GlpkException;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_iocp;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

public class Lp {

//    
//    Maximize
//  satisfacao:  10x1 + 6x2 + 4x3
//  
//Subject To
//  valor da cesta:   10x1 +  15x2 +  40x3 <=  400
//  categorias - Oleo:   1x1 +  1x2 +  0x3 <=  1
//  categorias - Banana: 0x1 + 0x2 + 1x3 <=  1
//  categorias - informatica:  0x1 + 0x2 + 1x3 <=  1
//  
//Bounds
//  x1 >=  0
//  x2 >=  0
//  x3 >=  0
//End
//
    public static void main(String[] arg) {
        calcularCesta(12);

    }

    public static Cesta calcularCesta(double max) {
        //select count(*) from produto

        int numCol = Persistencia.getPersistencia(1).getProdutoDAO().quantProd();

        //select count(*)+1 from categoria
        int numRow = Persistencia.getPersistencia(1).getCategoriaDAO().quantCat();

        //select produto
        List<Produto> lista = Persistencia.getPersistencia(1).getProdutoDAO().listarPorCat();

        glp_prob lp;
        glp_iocp parm;
        glp_smcp parm2;
        SWIGTYPE_p_int ind;
        SWIGTYPE_p_double val;

        int ret;
        try {
// Create problem
            lp = GLPK.glp_create_prob();
            System.out.println(" Problema criado ");
            GLPK.glp_set_prob_name(lp, "Problema da mochila ");
// Define columns
            GLPK.glp_add_cols(lp, numCol);// oito produtos

//            //banana caturra
//            GLPK.glp_set_col_name(lp, 1, " x1 ");
//            GLPK.glp_set_col_kind(lp, 1, GLPKConstants.GLP_BV);//GLP_BV integer variable
//            GLPK.glp_set_col_bnds(lp, 1, GLPKConstants.GLP_DB, 0, 1); // GLP_DB lb · x · ub Double-bounded variable
//
//            //banana maca
//            GLPK.glp_set_col_name(lp, 2, " x2 ");
//            GLPK.glp_set_col_kind(lp, 2, GLPKConstants.GLP_BV);
//            GLPK.glp_set_col_bnds(lp, 2, GLPKConstants.GLP_DB, 0, 1);
//
//            //banana ouro
//            GLPK.glp_set_col_name(lp, 3, " x3 ");
//            GLPK.glp_set_col_kind(lp, 3, GLPKConstants.GLP_BV);
//            GLPK.glp_set_col_bnds(lp, 3, GLPKConstants.GLP_DB, 0, 1);
//
//            //maca argentina
//            GLPK.glp_set_col_name(lp, 4, " x4 ");
//            GLPK.glp_set_col_kind(lp, 4, GLPKConstants.GLP_BV);
//            GLPK.glp_set_col_bnds(lp, 4, GLPKConstants.GLP_DB, 0, 1);
//
//            //maca argentina
//            GLPK.glp_set_col_name(lp, 5, " x5 ");
//            GLPK.glp_set_col_kind(lp, 5, GLPKConstants.GLP_BV);
//            GLPK.glp_set_col_bnds(lp, 5, GLPKConstants.GLP_DB, 0, 1);
//
//            //uva  grande
//            GLPK.glp_set_col_name(lp, 6, " x6 ");
//            GLPK.glp_set_col_kind(lp, 6, GLPKConstants.GLP_BV);
//            GLPK.glp_set_col_bnds(lp, 6, GLPKConstants.GLP_DB, 0, 1);
//
//            //uva  media
//            GLPK.glp_set_col_name(lp, 7, " x7 ");
//            GLPK.glp_set_col_kind(lp, 7, GLPKConstants.GLP_BV);
//            GLPK.glp_set_col_bnds(lp, 7, GLPKConstants.GLP_DB, 0, 1);
//
//            //uva  pequena
//            GLPK.glp_set_col_name(lp, 8, " x8 ");
//            GLPK.glp_set_col_kind(lp, 8, GLPKConstants.GLP_BV);
//            GLPK.glp_set_col_bnds(lp, 8, GLPKConstants.GLP_DB, 0, 1);
// Create constraints
            ind = GLPK.new_intArray(numCol);
            for (int i = 1; i <= numCol; i++) {
                GLPK.intArray_setitem(ind, i, i);
            }
//            
//            GLPK.intArray_setitem(ind, 1, 1);
//            GLPK.intArray_setitem(ind, 2, 2);
//            GLPK.intArray_setitem(ind, 3, 3);
//            GLPK.intArray_setitem(ind, 4, 4);
//            GLPK.intArray_setitem(ind, 5, 5);
//            GLPK.intArray_setitem(ind, 6, 6);
//            GLPK.intArray_setitem(ind, 7, 7);
//            GLPK.intArray_setitem(ind, 8, 8);

            GLPK.glp_add_rows(lp, numRow);// n categorias

            //restricao do valor
            GLPK.glp_set_row_name(lp, 1, "max");
            GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_DB, 0, max);
            val = GLPK.new_doubleArray(numCol);

            for (int i = 1; i <= numCol; i++) {

                GLPK.doubleArray_setitem(val, i, lista.get(i - 1).getValor());
            }
//            GLPK.doubleArray_setitem(val, 1, 1);
//            GLPK.doubleArray_setitem(val, 2, 2);
//            GLPK.doubleArray_setitem(val, 3, 3);
//            GLPK.doubleArray_setitem(val, 4, 2);
//            GLPK.doubleArray_setitem(val, 5, 4);
//            GLPK.doubleArray_setitem(val, 6, 5);
//            GLPK.doubleArray_setitem(val, 7, 6);
//            GLPK.doubleArray_setitem(val, 8, 8);

            GLPK.glp_set_mat_row(lp, 1, numCol, ind, val);
            GLPK.delete_doubleArray(val);

            int catAtual = 0;
            for (int i = 2; i <= numRow; i++) {
                catAtual = lista.get(i - 1).getCategoria().getId();
                GLPK.glp_set_row_name(lp, i, " categoria " + i);
                GLPK.glp_set_row_bnds(lp, i, GLPKConstants.GLP_DB, 0, 1);
                val = GLPK.new_doubleArray(numCol);
                for (int j = 1; j <= numCol; j++) {

                    GLPK.glp_set_col_name(lp, j, " x" + lista.get(j-1).getId());
                    GLPK.glp_set_col_kind(lp, j, GLPKConstants.GLP_BV);//GLP_BV integer variable
                    GLPK.glp_set_col_bnds(lp, j, GLPKConstants.GLP_DB, 0, 1); // GLP_DB lb · x · ub Double-bounded variable

                    if (lista.get(j-1).getCategoria().getId() == catAtual) {
                        GLPK.doubleArray_setitem(val, j, 1);
                    } else {
                        GLPK.doubleArray_setitem(val, j, 0);
                    }
                }

                GLPK.glp_set_mat_row(lp, i, numCol, ind, val);
                GLPK.delete_doubleArray(val);
            }

//            GLPK.doubleArray_setitem(val, 1, 1);
//            GLPK.doubleArray_setitem(val, 2, 1);
//            GLPK.doubleArray_setitem(val, 3, 1);
//            GLPK.doubleArray_setitem(val, 4, 0);
//            GLPK.doubleArray_setitem(val, 5, 0);
//            GLPK.doubleArray_setitem(val, 6, 0);
//            GLPK.doubleArray_setitem(val, 7, 0);
//            GLPK.doubleArray_setitem(val, 8, 0);
            //categoria 2
//            GLPK.glp_set_row_name(lp, 3, " categoria2");
//            GLPK.glp_set_row_bnds(lp, 3, GLPKConstants.GLP_DB, 0, 1);
//            val = GLPK.new_doubleArray(8);
//            GLPK.doubleArray_setitem(val, 1, 0);
//            GLPK.doubleArray_setitem(val, 2, 0);
//            GLPK.doubleArray_setitem(val, 3, 0);
//            GLPK.doubleArray_setitem(val, 4, 1);
//            GLPK.doubleArray_setitem(val, 5, 1);
//            GLPK.doubleArray_setitem(val, 6, 0);
//            GLPK.doubleArray_setitem(val, 7, 0);
//            GLPK.doubleArray_setitem(val, 8, 0);
//
//            GLPK.glp_set_mat_row(lp, 3, 8, ind, val);
//            GLPK.delete_doubleArray(val);
//
//            //categoria 3
//            GLPK.glp_set_row_name(lp, 4, " categoria3");
//            GLPK.glp_set_row_bnds(lp, 4, GLPKConstants.GLP_UP, 0, 1);
//            val = GLPK.new_doubleArray(8);
//            GLPK.doubleArray_setitem(val, 1, 0);
//            GLPK.doubleArray_setitem(val, 2, 0);
//            GLPK.doubleArray_setitem(val, 3, 0);
//            GLPK.doubleArray_setitem(val, 4, 0);
//            GLPK.doubleArray_setitem(val, 5, 0);
//            GLPK.doubleArray_setitem(val, 6, 1);
//            GLPK.doubleArray_setitem(val, 7, 1);
//            GLPK.doubleArray_setitem(val, 8, 1);
//
//            GLPK.glp_set_mat_row(lp, 4, 8, ind, val);
//            GLPK.delete_doubleArray(val);
//
//            GLPK.delete_intArray(ind);
//
// Define objective
            GLPK.glp_set_obj_name(lp, " z ");
            GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MAX);

            for (int i = 1; i <= numCol; i++) {
                GLPK.glp_set_obj_coef(lp, i, lista.get(i - 1).getSatisfacao());
            }

//            GLPK.glp_set_obj_coef(lp, 1, 2);
//            GLPK.glp_set_obj_coef(lp, 2, 3);
//            GLPK.glp_set_obj_coef(lp, 3, 2);
//            GLPK.glp_set_obj_coef(lp, 4, 1);
//            GLPK.glp_set_obj_coef(lp, 5, 4);
//            GLPK.glp_set_obj_coef(lp, 6, 5);
//            GLPK.glp_set_obj_coef(lp, 7, 4);
//            GLPK.glp_set_obj_coef(lp, 8, 10);
// Solve model
            parm2 = new glp_smcp();
            GLPK.glp_init_smcp(parm2);
            GLPK.glp_simplex(lp, parm2);
            parm = new glp_iocp();

            GLPK.glp_init_iocp(parm);
            ret = GLPK.glp_intopt(lp, parm);
// Retrieve solution
            if (ret == 0) {
                write_lp_solution(lp);
            } else {
                System.out.println(" The problem could not be solved ");
            }
// Free memory
            GLPK.glp_delete_prob(lp);
        } catch (GlpkException ex) {
            System.out.println(ex.getMessage());
        }
        return new Cesta();
    }

    /**
     * write simplex solution
     *
     * @param lp problem
     */
    static void write_lp_solution(glp_prob lp) {
        int i;
        int n;
        String name;
        double val;
        name = GLPK.glp_get_obj_name(lp);
        val = GLPK.glp_mip_obj_val(lp);
        System.out.print(name);
        System.out.print(" = ");
        System.out.println(val);
        n = GLPK.glp_get_num_cols(lp);
        for (i = 1; i <= n; i++) {
            name = GLPK.glp_get_col_name(lp, i);
            val = GLPK.glp_mip_col_val(lp, i);
            System.out.print(name);
            System.out.print(" = ");
            System.out.println(val);
        }
    }
}
