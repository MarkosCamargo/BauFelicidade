/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.cesta.solver;

import br.udesc.ceavi.cesta.core.MCKP;
import java.util.ArrayList;
import java.util.List;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.GlpkException;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_iocp;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

/**
 * 
 * @author ignoi
 */
public class Solver implements MCKP {

    @Override
    public List<Integer> calcular(double satisfacaoMax, int cols, int rows, int[] categorias, double[] satisfacoes, int[] produtos, double[] precos) {
        try {
            int i;
            int i2;
            glp_prob lp = GLPK.glp_create_prob();
            GLPK.glp_set_prob_name((glp_prob) lp, (String) "MCKP");
            GLPK.glp_add_cols((glp_prob) lp, (int) cols);
            SWIGTYPE_p_int ind = GLPK.new_intArray((int) cols);
            for (i2 = 1; i2 <= cols; ++i2) {
                GLPK.glp_set_col_name((glp_prob) lp, (int) i2, (String) (" x" + produtos[i2 - 1]));
                GLPK.glp_set_col_kind((glp_prob) lp, (int) i2, (int) GLPKConstants.GLP_BV);
                GLPK.glp_set_col_bnds((glp_prob) lp, (int) i2, (int) GLPKConstants.GLP_DB, (double) 0.0, (double) 1.0);
                GLPK.intArray_setitem((SWIGTYPE_p_int) ind, (int) i2, (int) i2);
            }
            GLPK.glp_add_rows((glp_prob) lp, (int) rows);
            GLPK.glp_set_row_name((glp_prob) lp, (int) 1, (String) "max");
            GLPK.glp_set_row_bnds((glp_prob) lp, (int) 1, (int) GLPKConstants.GLP_DB, (double) 0.0, (double) satisfacaoMax);
            SWIGTYPE_p_double val = GLPK.new_doubleArray((int) cols);
            for (i2 = 1; i2 <= cols; ++i2) {
                GLPK.doubleArray_setitem((SWIGTYPE_p_double) val, (int) i2, (double) precos[i2 - 1]);
            }
            GLPK.glp_set_mat_row((glp_prob) lp, (int) 1, (int) cols, (SWIGTYPE_p_int) ind, (SWIGTYPE_p_double) val);
            GLPK.delete_doubleArray((SWIGTYPE_p_double) val);
            int categoriaAtual = categorias[0];
            int cat = 0;
            for (i = 2; i <= rows; ++i) {
                GLPK.glp_set_row_name((glp_prob) lp, (int) i, (String) (" categoria " + i));
                GLPK.glp_set_row_bnds((glp_prob) lp, (int) i, (int) GLPKConstants.GLP_DB, (double) 0.0, (double) 1.0);
                val = GLPK.new_doubleArray((int) cols);
                for (int j = 1; j <= cols; ++j) {
                    cat = categorias[j - 1];
                    if (cat == categoriaAtual) {
                        System.out.println(1);
                        GLPK.doubleArray_setitem((SWIGTYPE_p_double) val, (int) j, (double) 1.0);
                        continue;
                    }
                    System.out.println(0);
                    GLPK.doubleArray_setitem((SWIGTYPE_p_double) val, (int) j, (double) 0.0);
                }
                GLPK.glp_set_mat_row((glp_prob) lp, (int) i, (int) cols, (SWIGTYPE_p_int) ind, (SWIGTYPE_p_double) val);
                GLPK.delete_doubleArray((SWIGTYPE_p_double) val);
                ++categoriaAtual;
            }
            GLPK.glp_set_obj_name((glp_prob) lp, (String) " z ");
            GLPK.glp_set_obj_dir((glp_prob) lp, (int) GLPKConstants.GLP_MAX);
            for (i = 1; i <= cols; ++i) {
                GLPK.glp_set_obj_coef((glp_prob) lp, (int) i, (double) satisfacoes[i - 1]);
            }
            glp_smcp parm2 = new glp_smcp();
            GLPK.glp_init_smcp((glp_smcp) parm2);
            GLPK.glp_simplex((glp_prob) lp, (glp_smcp) parm2);
            glp_iocp parm = new glp_iocp();
            GLPK.glp_init_iocp((glp_iocp) parm);
            int ret = GLPK.glp_intopt((glp_prob) lp, (glp_iocp) parm);
            if (ret == 0) {
                Solver.write_lp_solution(lp);
                ArrayList<Integer> listaId = new ArrayList<Integer>();
                int n = GLPK.glp_get_num_cols((glp_prob) lp);
                for (int i3 = 1; i3 <= n; ++i3) {
                    String nome = GLPK.glp_get_col_name((glp_prob) lp, (int) i3);
                    double valor = GLPK.glp_mip_col_val((glp_prob) lp, (int) i3);
                    if (valor != 1.0) {
                        continue;
                    }
                    listaId.add(Integer.parseInt(nome.replace("x", "").trim()));
                }
                return listaId;
            }
            System.out.println("The problem could not be solved.");
            GLPK.glp_delete_prob((glp_prob) lp);
        } catch (GlpkException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private static void write_lp_solution(glp_prob lp) {
        String name = GLPK.glp_get_obj_name((glp_prob) lp);
        double val = GLPK.glp_mip_obj_val((glp_prob) lp);
        System.out.print(name);
        System.out.print(" = ");
        System.out.println(val);
        int n = GLPK.glp_get_num_cols((glp_prob) lp);
        for (int i = 1; i <= n; ++i) {
            name = GLPK.glp_get_col_name((glp_prob) lp, (int) i);
            val = GLPK.glp_mip_col_val((glp_prob) lp, (int) i);
            System.out.print(name);
            System.out.print(" = ");
            System.out.println(val);
        }
    }
}
