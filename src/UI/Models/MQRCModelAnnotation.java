/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Models;

import MQApi.Result.Annotations.*;
import MQApi.Enums.VariableType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author jzhou
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MQRCModelAnnotation {
    String ExcelKeyword();
    String CommandKeyWord() default "";
    boolean loop() default true;
    boolean createNewModel() default false;
}
