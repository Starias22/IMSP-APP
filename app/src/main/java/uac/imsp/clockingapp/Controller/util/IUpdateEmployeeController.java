package uac.imsp.clockingapp.Controller.util;

import android.graphics.Bitmap;

public interface IUpdateEmployeeController {

    void onUpdateEmployee(String mail, String selectedService, int startTime,


                          int endTime,byte[] workDays, Bitmap picture, String type,
						  boolean isAdmin);




    void onReset();

	void onUpdateEmployee(boolean emailUpdated, boolean serviceUpdated, boolean planningUpdated, boolean pictureUpdated, boolean typeUpdated);

	Object[] onLoad(int actionNumber);
}
