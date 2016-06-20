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

        glp_prob lp;
        glp_iocp parm;
        glp_smcp parm2;
        SWIGTYPE_p_int ind;
        SWIGTYPE_p_double val;

        int ret;
        try {
            lp = GLPK.glp_create_prob();
            GLPK.glp_set_prob_name(lp, "MCKP");
            GLPK.glp_add_cols(lp, cols);
            ind = GLPK.new_intArray(cols);
            for (int i = 1; i <= cols; i++) {

                GLPK.glp_set_col_name(lp, i, "" + produtos[i - 1]);
                GLPK.glp_set_col_kind(lp, i, GLPKConstants.GLP_BV);
                GLPK.glp_set_col_bnds(lp, i, GLPKConstants.GLP_DB, 0, 1);
                GLPK.intArray_setitem(ind, i, i);
            }
            //Creates the amount of restrictions 1 + number of categories
            GLPK.glp_add_rows(lp, rows);

            //Defines parameters for the first row
            GLPK.glp_set_row_name(lp, 1, "max");
            GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_DB, 0, satisfacaoMax);
            val = GLPK.new_doubleArray(cols + 1);
            for (int i = 1; i <= cols; i++) {
                GLPK.doubleArray_setitem(val, i, precos[i - 1]);
            }
            GLPK.glp_set_mat_row(lp, 1, cols, ind, val);
            GLPK.delete_doubleArray(val);
            int[] distinctCategorias = new int[rows - 1];
            distinctCategorias[0] = categorias[0];
            int indiceAtual = 0;

            for (int i = 0; i < categorias.length; i++) {
                if (categorias[i] != distinctCategorias[indiceAtual]) {
                    indiceAtual++;
                    distinctCategorias[indiceAtual] = categorias[i];

                }
            }

            for (int i = 2; i <= rows; i++) {
                ;
                GLPK.glp_set_row_name(lp, i, " categoria " + i);
                GLPK.glp_set_row_bnds(lp, i, GLPKConstants.GLP_DB, 0, 1);
                val = GLPK.new_doubleArray(cols + 1);
                for (int j = 1; j <= cols; j++) {

                    if (distinctCategorias[i - 2] == categorias[j - 1]) {
                        GLPK.doubleArray_setitem(val, j, 1);
                    } else {
                        GLPK.doubleArray_setitem(val, j, 0);
                    }
                }
                GLPK.glp_set_mat_row(lp, i, cols, ind, val);
                GLPK.delete_doubleArray(val);

            }

            GLPK.glp_set_obj_name(lp, " z ");
            GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MAX);
            for (int i = 1; i <= cols; i++) {
                GLPK.glp_set_obj_coef(lp, i, satisfacoes[i - 1]);
            }
            parm2 = new glp_smcp();
            GLPK.glp_init_smcp(parm2);
            GLPK.glp_simplex(lp, parm2);
            parm = new glp_iocp();
            GLPK.glp_init_iocp(parm);
            ret = GLPK.glp_intopt(lp, parm);
            if (ret == 0) {
                write_lp_solution(lp);
                List<Integer> listaId = new ArrayList<Integer>();
                int n = GLPK.glp_get_num_cols(lp);
                for (int i = 1; i <= n; i++) {
                    String nome = GLPK.glp_get_col_name(lp, i);
                    double valor = GLPK.glp_mip_col_val(lp, i);
                    if (valor == 1) {

                        listaId.add(Integer.parseInt(nome.replace("x", "").trim()));

                    }
                }
                return listaId;
            } else {
                System.out.println("The problem could not be solved.");
            }
            GLPK.glp_delete_prob(lp);
        } catch (GlpkException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private static void write_lp_solution(glp_prob lp) {
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
