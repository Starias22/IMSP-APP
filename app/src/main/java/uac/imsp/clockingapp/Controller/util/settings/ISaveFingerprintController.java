package uac.imsp.clockingapp.Controller.util.settings;

public interface ISaveFingerprintController {

	void onFingerprintEnrollement(int currentUser, byte[] fingerprintData);
}
