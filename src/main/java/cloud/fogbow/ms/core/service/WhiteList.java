package cloud.fogbow.ms.core.service;

import cloud.fogbow.common.util.HomeDir;
import cloud.fogbow.ms.MembershipService;

import cloud.fogbow.ms.constants.ConfigurationPropertyKeys;
import org.apache.log4j.Logger;
import cloud.fogbow.ms.constants.Messages;
import cloud.fogbow.ms.constants.SystemConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WhiteList implements MembershipService {

    private static final Logger LOGGER = Logger.getLogger(WhiteList.class);

    private List<String> membersList;

    public WhiteList(String membershipConfPath) throws FileNotFoundException {
        this.membersList = readMembersFromFile(membershipConfPath);
    }

    public WhiteList() throws FileNotFoundException {
        this.membersList = readMembersFromFile(SystemConstants.CONF_FILE_NAME);
    }

    /**
     * Read list of XMPP members ID from membership config file.
     *
     */
    @Override
    public List<String> listMembers() {
        return this.membersList;
    }

    private List<String> readMembersFromFile(String membershipConfPath) throws FileNotFoundException {
        Properties properties = new Properties();
        InputStream input = new FileInputStream(HomeDir.getPath() + membershipConfPath);
        List<String> membersList = new ArrayList<>();

        try {
            properties.load(input);

            String membersListStr = properties.getProperty(ConfigurationPropertyKeys.MEMBERS_LIST_KEY);
            for (String member : membersListStr.split(",")) {
                member = member.trim();
                membersList.add(member);
            }
        } catch (IOException e) {
            LOGGER.warn(String.format(Messages.Warn.ERROR_READING_CONF_FILE, membershipConfPath), e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                LOGGER.warn(String.format(Messages.Warn.ERROR_CLOSING_CONF_FILE, membershipConfPath), e);
            }
        }

        return membersList;
    }
}
