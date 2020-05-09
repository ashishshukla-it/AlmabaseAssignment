# AlmabaseAssignment
API to get repositories for an organization

Sample url:
http://localhost:8080/AlmabaseAssignment/orgRepoForkCount/?organization=microsoft&topN=7&commiters=3

Query Parameters:
organization- Accepts an String. It is the name of the organiztion whose repositories have to be seached.

topN – Accepts an Integer. It is the number of repositories that api consumer want to see. Results are sorted in descending on the number of forks for that.
 repositoriy. 

commiters- Accepts an Integer. It is the number of committees and their commit counts.

Sample response:
[
    {
        "repoName": "plcrashreporter",
        "forks": 338,
        "topCommiters": [
            {
                "commiterName": "GitHub",
                "commitCount": 20
            },
            {
                "commiterName": "Ivan Matkov",
                "commitCount": 4
            },
            {
                "commiterName": "Clement Polet",
                "commitCount": 2
            }
        ]
    },
    {
        "repoName": "php-sdk-binary-tools",
        "forks": 54,
        "topCommiters": [
            {
                "commiterName": "Christoph M. Becker",
                "commitCount": 18
            },
            {
                "commiterName": "Anatol Belski",
                "commitCount": 12
            }
        ]
    },
    {
        "repoName": "cocos2d-x",
        "forks": 40,
        "topCommiters": [
            {
                "commiterName": "minggo",
                "commitCount": 23
            },
            {
                "commiterName": "GitHub",
                "commitCount": 6
            },
            {
                "commiterName": "CocosRobot#Set",
                "commitCount": 1
            }
        ]
    },
    {
        "repoName": "WindowsAzureToolkitForEclipseWithJava",
        "forks": 36,
        "topCommiters": [
            {
                "commiterName": "v-prajpe",
                "commitCount": 8
            },
            {
                "commiterName": "Suresh Nallamilli",
                "commitCount": 7
            },
            {
                "commiterName": "snallami",
                "commitCount": 7
            }
        ]
    },
    {
        "repoName": "phone-info",
        "forks": 22,
        "topCommiters": [
            {
                "commiterName": "Tomi Paananen",
                "commitCount": 26
            },
            {
                "commiterName": "Jiihaa",
                "commitCount": 3
            },
            {
                "commiterName": "Jarkko Aura",
                "commitCount": 1
            }
        ]
    },
    {
        "repoName": "rss-reader-wp",
        "forks": 21,
        "topCommiters": [
            {
                "commiterName": "Mikko Piipponen",
                "commitCount": 4
            },
            {
                "commiterName": "Tomi Paananen",
                "commitCount": 4
            }
        ]
    },
    {
        "repoName": "here-launchers",
        "forks": 21,
        "topCommiters": [
            {
                "commiterName": "Tomi Paananen",
                "commitCount": 11
            },
            {
                "commiterName": "Lucian Tomuta",
                "commitCount": 3
            },
            {
                "commiterName": "Dr. Jukka",
                "commitCount": 1
            }
        ]
    }
]



