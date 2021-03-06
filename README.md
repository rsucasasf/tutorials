# tutorials

Mini tutorials and links collections. The content of each of the **table of contents'** main sections can be found in their respective folders, except the *Others* content which is located in this document.

-----------------------

**Table of Contents / Folders**

- [Maven](docs/maven/Readme.md)
  - [Deploying to Maven Central](docs/maven/deploy.md)
  - Export to maven (local repo)
  - Check code - PMD reports
  - Code coverage - jacoco
- [Travis](docs/travis/Readme.md)
  - [Getting Started](https://docs.travis-ci.com/user/getting-started/)
  - Support settings for multiple languges in one travis ci file
  - Github - Integration with Travis
  - [Customizing the Build](https://docs.travis-ci.com/user/customizing-the-build)
  - [Building a Clojure project](https://docs.travis-ci.com/user/languages/clojure/)
- [Clojure](docs/clojure/Readme.md)
  - Requirements
  - [Readable Clojure](http://tonsky.me/blog/readable-clojure/)
  - [Deploying to Maven Central](DEPLOY.md)
  - Leiningen
    - Create a web project
    - Tutorial
  - Export to maven (local repo)
  - gen-class
  - Deploying to CLOJARS
    - Update *.jar*
  - Check code
- [Collectd](docs/collectd/Readme.md)
  - Install
  - Commands
  - Custom plugins (written in C)
- [Docker](docs/docker/Readme.md)
  - Install
  - Commands
- [IaaS](docs/iaas/Readme.md)
  - Apache Brooklyn
- Others
  - Continuous Integrations
  - Markdown tips and links
  - jMonkeyEngine
  - Github / Bitbucket
    - Include external project (from repo)
    - Configure bitbucket-pipelines.yml
    - Readme markup tips / examples
    - Create a new branch with git and manage branches

-----------------------

### Continuous Integration

- Tests / builds
  - [Travis](https://travis-ci.org/profile)

  - [codeship](https://app.codeship.com)

    Codeship is a fully customizable Continuous Integration service in the cloud.

- Code coverage
  - [codecov](https://codecov.io)

    Codecov provides highly integrated tools to group, merge, archive and compare coverage reports. Whether your team is comparing changes in a pull request or reviewing a single commit, Codecov will improve the code review workflow and quality.

### Markdown tips and links

[Markdown License badges](https://gist.github.com/lukas-h/2a5d00690736b4c3a7ba)

Other badges from [shields.io](https://shields.io/)

### jMonkeyEngine

[jMonkeyEngine Tutorials and Documentation](http://davidb.github.io/sandbox_wiki_jme/jme3.html)

### Github / Bitbucket

#### Include external project (from repo)

1. git submodule add https://bitbucket.org/jaredw/awesomelibrary awesomelibrary

2. commit & push

#### Configure bitbucket-pipelines.yml

**pipelines** use docker images.

https://stackoverflow.com/questions/40166537/is-it-possible-to-use-multiple-docker-images-in-bitbucket-pipeline

[Configure bitbucket-pipelines.yml](https://confluence.atlassian.com/bitbucket/configure-bitbucket-pipelines-yml-792298910.html)

```yaml
# This is a sample build configuration.
# Only use spaces to indent your bitbucket-pipelines.yml configuration.
# -----
# You can specify a custom docker image from Docker Hub as your build environment.
image: node:4.6.0

pipelines:
  custom: # Pipelines that are triggered manually
    sonar: # The name that is displayed in the list in the Bitbucket Cloud GUI
      - step:
          script:
            - echo "Manual triggers for Sonar are awesome!"
    deployment-to-prod: # Another display name
      - step:
          script:
            - echo "Manual triggers for deployments are awesome!"
  branches:  # Pipelines that run automatically on a commit to a branch
    staging:
      - step:
          script:
            - echo "Automated pipelines are cool too."
```

#### Readme markup tips / examples

Icons for markup: [http://www.webpagefx.com/tools/emoji-cheat-sheet/](http://www.webpagefx.com/tools/emoji-cheat-sheet/)

#### [Create a new branch with git and manage branches](https://github.com/Kunena/Kunena-Forum/wiki/Create-a-new-branch-with-git-and-manage-branches)

**NOTE:** This article was taken from https://github.com/Kunena/Kunena-Forum/wiki

In your github fork, you need to keep your master branch clean, by clean I mean without any changes, like that you can create at any time a branch from your master. Each time, that you want to commit a bug or a feature, you need to create a branch for it, which will be a copy of your master branch.

When you do a pull request on a branch, you can continue to work on another branch and make another pull request on this other branch.

Before creating a new branch, pull the changes from upstream. Your master needs to be up to date.

Create the branch on your local machine and switch in this branch :

<pre>$ git checkout -b [name_of_your_new_branch]</pre>

Change working branch :

<pre>$ git checkout [name_of_your_new_branch]</pre>

Push the branch on github :

<pre>$ git push origin [name_of_your_new_branch]</pre>

When you want to commit something in your branch, be sure to be in your branch.

You can see all branches created by using :

<pre>$ git branch</pre>

Which will show :

<pre>* approval_messages
  master
  master_clean</pre>

Add a new remote for your branch :

<pre>$ git remote add [name_of_your_remote] <url></pre>

Push changes from your commit into your branch :

<pre>$ git push [name_of_your_new_remote] [name_of_your_branch]</pre>

Update your branch when the original branch from official repository has been updated :

```sh
$ git fetch [name_of_your_remote]
```
Then you need to apply to merge changes, if your branch is derivated from develop you need to do :

<pre>$ git merge [name_of_your_remote]/develop</pre>

Delete a branch on your local filesystem :

<pre>$ git branch -d [name_of_your_new_branch]</pre>

To force the deletion of local branch on your filesystem :

<pre>$ git branch -D [name_of_your_new_branch]</pre>

Delete the branch on github :

<pre>$ git push origin :[name_of_your_new_branch]</pre>

The only difference is the : to say delete, you can do it too by using github interface to remove branch : https://help.github.com/articles/deleting-unused-branches.

If you want to change default branch, it's so easy with github, in your fork go into Admin and in the drop-down list default branch choose what you want.
