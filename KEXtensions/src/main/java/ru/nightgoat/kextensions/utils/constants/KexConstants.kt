package ru.nightgoat.kextensions.utils.constants

object KexConstants {
    const val IP_ADDRESS_PATTERN =
        ("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                + "|[1-9][0-9]|[0-9]))")

    const val PHONE_PATTERN = "(\\+[0-9]+[\\- \\.]*)?" + // +<digits><sdd>*
            "(\\([0-9]+\\)[\\- \\.]*)?" +                        // (<digits>)<sdd>*
            "([0-9][0-9\\- \\.]+[0-9])"                          // <digit><digit|sdd>+<digit>


    const val EMAIL_PATTERN =
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"

    const val ONLY_DIGITS_PATTERN = "^[0-9]+$"

    const val ZERO_STRING = "0"
    const val EMPTY_STRING = ""
}