---
title: Dotty
---

.. class:: page red

    .. class:: scala-logo-container
    .. image:: /images/scala-logo.svg

    =====
    Dotty
    =====

    .. class:: centered-subtitle

        A next generation compiler for Scala


    .. raw:: html

        <div class="scroll-down-container">
            <i id="scroll-down-arrow" class="animated infinite pulse material-icons">
                expand_more
            </i>
        </div>


.. class:: page white

    .. class:: centered

         ===============
         Getting Started
         ===============

         Using Dotty to compile your project is now pretty simple. Create the
         following structure:

         .. code-block:: bordered

                .
                ├── build.sbt
                ├── project
                │   ├── build.properties
                │   └── plugins.sbt
                └── src

         ---------
         build.sbt
         ---------

         .. code-block:: bordered

             name := "application"
             version := "0.1"
             enablePlugins(DottyPlugin)

         ----------------
         build.properties
         ----------------

         .. code-block:: bordered

             sbt.version=0.13.11

         -----------
         plugins.sbt
         -----------

         .. code-block:: bordered

             addSbtPlugin("com.felixmulder" % "sbt-dotty" % "0.1")
