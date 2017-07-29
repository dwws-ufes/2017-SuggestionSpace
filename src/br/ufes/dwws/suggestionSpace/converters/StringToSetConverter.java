package br.ufes.dwws.suggestionSpace.converters;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="str2listConverter")
public class StringToSetConverter implements Converter
{
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component, final String values)
    {
        Set<String> result = new HashSet<String>(); 
        for (String value : values.split(",", -1))
        {           
            final String trimmedValue = value.trim();
            result.add(new String(trimmedValue));
        }       
        return result;
    }

    @Override
    public String getAsString(final FacesContext context, final UIComponent component, final Object value)
    {
        if (value instanceof Set<?>)
        {
            final StringBuffer result = new StringBuffer();
            final Set<?> set = (Set<?>) value;
        	Object element = null;
            for (Iterator<?> it = set.iterator(); it.hasNext(); ) {
            	element = it.next();
            	if (element instanceof String) {
            		result.append(((String) element));
                    result.append(",");
            	}
            }
            return result.toString().substring(0, result.toString().length()-1);
        }
        else
        {
            throw new IllegalArgumentException( "Cannot convert " + value + " object to List." );
        }
    }
}