# set the ssl verification env variable -- if set to 0, can't verify communication with the expected peer
$ENV{'PERL_LWP_SSL_VERIFY_HOSTNAME'} = 0;

require HTTP::Request;
require LWP::UserAgent;

use constant NUM_LINK_REFRESH => 5;

if (initialize() == True) {
    # explore all the way through the links in the document as many times as specified
    for (my $i = 0; $i < NUM_LINK_REFRESH; $i++) {
        my @links = ();
        while (my $row = <$urls>) {
            my @baseUrl = $row =~ /(http.*\/\/[A-Za-z.0-9\-]*)[\/|\s]/g;
            # cleaning the url input
            chomp $row;
            chomp @baseUrl[0];
            # get matches
            my $response = followLink($row);
            @foundLinks = getMatchUrls($response);
            # iterate over matches
            foreach $link (@foundLinks) {
                # add new links to the url file
                my $newAddLink = (substr($link, 0, 1) eq "/") ? (@baseUrl[0] . $link) : $link;
                print $newAddLink . "\n";
                push @links, ($newAddLink . "\n");
            }
        }
        my $position = tell($urls);
        seek $urls, 0, SEEK_END;
        # adds new urls to the end of the file
        foreach $newAddition (@links) {
            print $urls $newAddition;
        }
        seek $urls, $position, 0; # revert pointer back to its original position pre-addition
        
    }
}

#truncate "urls.txt", 0;


# follows links and returns the full response from the webpage
# param 0: string, link to follow
sub followLink {
    # creates an http get request
    my $req = HTTP::Request->new(GET => $_[0]);
    # agent for sending and receiving web requests
    my $ua = LWP::UserAgent->new;
    return $ua->request($req);  # syntax: -> used for accessing class members
}

# checks web response against url regex and returns matches
# param 0: list response from webpage
sub getMatchUrls {
    my @list = $_[0]->content =~ /href="([A-Za-z0-9.\/:]*)"/g;     # scans response for matches
    if (@list) {        # if list has anything in it
        return @list;
    }
}

# loads the given file in the specified mode
# param 0: string, file name
# param 1: string, mode to open the file in
sub openFile {
    open(my $file, $_[1], $_[0]) || die "Couldn't open file " . $_[0];
    return $file;
}

# sets up the program with the tools it needs (e.g., non-empty urls file)
# returns boolean, whether program is ready to run the rest yet or not
sub initialize {
    $urls = openFile("urls.txt", "+<");         # opened in read-write mode
    $emails = openFile("emails.txt", ">");      # opened in append mode
    
    # ensure urls file isn't empty
    if (-s "urls.txt" == 0) {
        print("Please enter a starting URL for the crawler:");
        my $input = <STDIN>;
        if ($input =~ /([A-Za-z0-9.:\/]*)/) {
            print $urls $input . "\n";
            return True;
        } else {
            print("Program was unable to recognize your URL. Please try again.");
            return False;
        }
    }
}

