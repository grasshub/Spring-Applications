#!--
#!-- Member Stories
#!--
#!-- These are all JBehave: B.D.D. stories.
#!-- Kios Stories for Airport location's.

Meta:
@tag DVD_Kiosk_Stories:Airport_Location

Narrative:
  In order to find DVDs that I would like to rent
  As a potential customer
  I want to be able to search for DVD rental locations.

Scenario: Should list all Airport locations for a member.
Given The member should have 3 locations available story
Then the dvd rental location for CDG-1 is Charles De Gaulle

Scenario: Should list all Airport locations for a member TWO.
Given The member should have 3 locations available story
Then the dvd rental location for CDG-1 is Charles De Gaulle
