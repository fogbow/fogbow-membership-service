package org.fogbowcloud.membershipservice.http;

import org.fogbowcloud.membershipservice.MembershipService;
import org.fogbowcloud.membershipservice.service.WhiteList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = MembershipController.ENDPOINT)
public class MembershipController {

    protected static final String ENDPOINT = "members";

    private MembershipService membershipService;

    public MembershipController() {
        this.membershipService = new WhiteList();
    }

    /**
     * Gets JSON response with a list of XMPP members ID.
     */
    @GetMapping
    public ResponseEntity<List<String>> listMembers() {
        try {
            List<String> membersId = this.membershipService.listMembers();
            return new ResponseEntity<>(membersId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}