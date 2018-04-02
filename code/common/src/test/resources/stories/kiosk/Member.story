#!--
#!-- Member Registration Stories
#!--
#!-- Member Registration Stories

Meta:
@tag DVD_Kiosk_Stories:Member_Registration

Narrative:
  In order to rent DVD's
  a member should be registered in the system.

Scenario: Validate member in the DVD rental system.
Given the member enters username j.smith and password hiking
Then the member will be validated as userid 93941

Scenario: Verify invalid member in the DVD rental system.
Given the member enters username j.smith and incorrect password wrongPassword
Then the member will be validated as userid invalid

