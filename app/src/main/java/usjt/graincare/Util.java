package usjt.graincare;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;

/**
 * Created by kaio on 21/05/2016.
 */
public class Util {
    public static Drawable getDrawable(Activity context, String drawableName){
        //troca hifen por underline, pois hifen e invalido para nome de recursos
        drawableName = drawableName.replace('-', '_');
        drawableName = drawableName.replace('â', 'a');
        //procurar a imagem por meio de reflection
        Class<?> c = R.drawable.class;
        try {
            Field idField = c.getDeclaredField(drawableName);
            int id = idField.getInt(idField);
            return context.getResources().getDrawable(id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return context.getResources().getDrawable(R.drawable.silo2);
    }
}