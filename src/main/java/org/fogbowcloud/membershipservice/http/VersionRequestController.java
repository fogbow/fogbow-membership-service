package org.fogbowcloud.membershipservice.http;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = VersionRequestController.VERSION_ENDPOINT)
public class VersionRequestController {

    public static final String VERSION_ENDPOINT = "version";

    private final Logger LOGGER = Logger.getLogger(VersionRequestController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getVersion() {
        String versionNumber = getVersionNumber();
        return new ResponseEntity<>(versionNumber, HttpStatus.OK);
    }

    public String getVersionNumber() {
        return MembershipController.API_VERSION_NUMBER;
    }
}
