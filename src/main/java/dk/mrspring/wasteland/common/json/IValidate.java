package dk.mrspring.wasteland.common.json;

/**
 * Created on 15-09-2015 for TheWastelandMod.
 */
public interface IValidate<T extends IValidate>
{
    void validate(T defaultValue);
}
